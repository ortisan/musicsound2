package ortiz.tests;

import org.junit.Test;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.InputStream;

public class SoundInfoTest {

    @Test
    public void getInfo440() throws Exception {


//        sampleRate = 44100.0
//        sampleSizeInBits = 8
//        channels = 1
//        frameSize = 1
//        frameRate = 44100.0
//        bigEndian = false
//        properties = null
        InputStream inputStream440MonoTone = SoundInfoTest.class.getResourceAsStream("/440.wav");
        AudioInputStream ais440MonoTone = AudioSystem.getAudioInputStream(inputStream440MonoTone);
        System.out.println("ais440.getFormat() = " + ais440MonoTone.getFormat());


//        sampleRate = 44100.0
//        sampleSizeInBits = 8
//        channels = 2
//        frameSize = 2
//        frameRate = 44100.0
//        bigEndian = false
//        properties = null
        InputStream inputStream440StereoTone = SoundInfoTest.class.getResourceAsStream("/440StereoToneGenerator.wav");
        AudioInputStream ais440StereoTone = AudioSystem.getAudioInputStream(inputStream440StereoTone);
        System.out.println("ais440.getFormat() = " + ais440StereoTone.getFormat());

    }


}
