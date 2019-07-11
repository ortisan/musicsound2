package ortiz.utils;

import javax.sound.sampled.AudioFormat;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class Configs {
    private static float INITIAL_DELTA_TIME = 0.1f;
    private AudioFormat audioFormat;
    private float sampleRate;
    private int sampleSizeInBits;
    private int frameSize;
    private int channels;
    private boolean bigEndian;
    private int numBytesPerSample;
    private int windowSize;
    private float timeDelta;
    private double f0;

    public Configs(AudioFormat audioFormat) {
        this.audioFormat = audioFormat;
        this.sampleRate = audioFormat.getSampleRate();
        this.sampleSizeInBits = audioFormat.getSampleSizeInBits();
        this.frameSize = audioFormat.getFrameSize();
        this.channels = audioFormat.getChannels();
        this.bigEndian = audioFormat.isBigEndian();
        // Fourier needs multiple of exponential 2
        int logSample2 = (int) Math.floor((Math.log(this.sampleRate * INITIAL_DELTA_TIME) / Math.log(2)));
        int numsSamplesWindow = (int) Math.pow(2, logSample2);
        this.numBytesPerSample = (frameSize * channels);
        this.windowSize = (int) numsSamplesWindow * this.numBytesPerSample;
        BigDecimal numsSamplesBig = new BigDecimal(numsSamplesWindow);
        BigDecimal sampleRateBig = BigDecimal.valueOf(sampleRate);
        timeDelta = numsSamplesBig.divide(sampleRateBig, 4, RoundingMode.HALF_UP).floatValue();
        f0 = BigDecimal.ONE.divide(new BigDecimal(timeDelta), 4, RoundingMode.HALF_UP).doubleValue();
    }

    public AudioFormat getAudioFormat() {
        return audioFormat;
    }

    public float getSampleRate() {
        return sampleRate;
    }

    public int getSampleSizeInBits() {
        return sampleSizeInBits;
    }

    public int getFrameSize() {
        return frameSize;
    }

    public int getChannels() {
        return channels;
    }

    public boolean isBigEndian() {
        return bigEndian;
    }

    public int getNumBytesPerSample() {
        return numBytesPerSample;
    }

    public int getWindowSize() {
        return windowSize;
    }

    public float getTimeDelta() {
        return timeDelta;
    }

    public double getF0() {
        return f0;
    }
}
