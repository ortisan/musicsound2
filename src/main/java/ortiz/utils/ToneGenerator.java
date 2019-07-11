package ortiz.utils;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ToneGenerator {

    public static void main(String[] args) {

        try {
            ToneGenerator.createTone(440, 100, 1); // A
            ToneGenerator.createTone(523.25, 100, 1); // C
        } catch (LineUnavailableException | IOException exc) {
            System.out.println(exc);
        }
    }

    public static void createTone(double hertz, int volume, int channels)
            throws LineUnavailableException, IOException {

        if (channels < 1 && channels > 2) {
            throw new IllegalArgumentException("Number of channel allowed is 1 (Mono), 2 (Stereo)");
        }

        int rate = 44100;
        byte[] buf = new byte[rate * channels];
        AudioFormat audioF = new AudioFormat(rate, 16, channels, true, false);
        //sampleRate, sampleSizeInBits, channels, signed, bigEndian

        SourceDataLine sourceDL = AudioSystem.getSourceDataLine(audioF);
        sourceDL = AudioSystem.getSourceDataLine(audioF);
        sourceDL.open(audioF);
        sourceDL.start();

        for (int i = 0; i < buf.length; i++) {
            double angle = ((i / channels) / (float) rate) * hertz * 2.0 * Math.PI;
            if (channels == 1) {
                buf[i] = (byte) (Math.sin(angle) * volume);
            } else {
                buf[i++] = (byte) (Math.sin(angle) * volume);
                buf[i] = (byte) (Math.cos(angle) * volume);
            }
        }
        sourceDL.write(buf, 0, buf.length);
        sourceDL.drain();
        sourceDL.stop();
        sourceDL.close();


        InputStream input = new ByteArrayInputStream(buf);
        final AudioInputStream ais = new AudioInputStream(input, audioF, buf.length
                / audioF.getFrameSize());
        AudioSystem.write(ais,  AudioFileFormat.Type.WAVE, new File("./" + hertz + ".wav"));

    }
}
