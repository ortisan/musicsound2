package ortiz.reactive;

import io.reactivex.Observable;
import ortiz.model.AmplitudeFrequency;
import ortiz.model.RawAudioMetadata;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;
import ortiz.utils.ByteUtils;
import ortiz.utils.Configs;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
import java.util.stream.IntStream;

public class AudioProcessor implements Closeable {

    private InputStream inputStream;

    private AudioInputStream audioInputStream;

    public AudioProcessor(File audioFile) throws IOException, UnsupportedAudioFileException {
        this.inputStream = new FileInputStream(audioFile);
        this.audioInputStream = AudioSystem.getAudioInputStream(inputStream);
    }

    public AudioProcessor(InputStream inputStream) throws IOException, UnsupportedAudioFileException {
        this.audioInputStream = AudioSystem.getAudioInputStream(inputStream);
    }

    public AudioProcessor(AudioInputStream audioInputStream) {
        this.audioInputStream = audioInputStream;
    }


    public Observable getObservable() {

        Configs configs = new Configs(audioInputStream.getFormat());

        Observable<RawAudioMetadata> observable = Observable.create(s -> {
            try {
                byte[] buffer = new byte[configs.getWindowSize()];
                for (int i = 0; audioInputStream.read(buffer) > 0; i++) {
                    s.onNext(new RawAudioMetadata(buffer, i, configs));
                }
                s.onComplete();
            } catch (Exception exc) {
                s.onError(exc);
            }
        });

        observable.map(rawAudioMetadata -> {
            // Gets the frequencies and amplitudes from fourier theory
            double[] waveSampled = ByteUtils.toDouble(rawAudioMetadata.getPayload(), configs.isBigEndian(), configs.getNumBytesPerSample());
            FastFourierTransformer fft = new FastFourierTransformer(DftNormalization.STANDARD);
            Complex[] complexes = fft.transform(waveSampled, TransformType.FORWARD);


            IntStream.range(0, complexes.length).mapToObj(i -> {
                double amplitude = Math.sqrt(Math.pow(complexes[i].getReal(), 2) + Math.pow(complexes[i].getImaginary(), 2));
                double frequency = configs.getF0() * i;
                return new AmplitudeFrequency(amplitude, frequency);

            }).filter(amplitudeFrequencyTime -> amplitudeFrequencyTime.getFrequency() >= 10 && amplitudeFrequencyTime.getFrequency() <= 10000)
                    .sorted((AmplitudeFrequency aft1, AmplitudeFrequency aft2) -> aft2.getAmplitude().compareTo(aft1.getAmplitude()))
                    .limit(10);

            return null;

            // TODO MAKE AVERAGE OF FREQUENCY AND ADD LOGIC OF TIMING.


        });
        return observable;
    }

    @Override
    public void close() throws IOException {
        if (this.audioInputStream != null) {
            this.audioInputStream.close();
        }
        if (this.inputStream != null) {
            this.inputStream.close();
        }
    }
}
