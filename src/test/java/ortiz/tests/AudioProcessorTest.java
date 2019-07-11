package ortiz.tests;

import org.junit.Assert;
import org.junit.Test;
import ortiz.reactive.AudioProcessor;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public class AudioProcessorTest {

    @Test
    public void testFrequencieLA() throws URISyntaxException, IOException, UnsupportedAudioFileException {
        InputStream resourceAsStream = AudioProcessorTest.class.getResourceAsStream("/440.wav");
        try (AudioProcessor processor = new AudioProcessor(resourceAsStream)) {
            processor.getObservable().subscribe(amplitudeFrequency -> {
                Assert.assertEquals("A", amplitudeFrequency.getNote());
            });
        }

    }

    @Test
    public void testFrequencieDO() throws URISyntaxException, IOException, UnsupportedAudioFileException {
        InputStream resourceAsStream = AudioProcessorTest.class.getResourceAsStream("/523_25.wav");
        try (AudioProcessor processor = new AudioProcessor(resourceAsStream)) {
            processor.getObservable().subscribe(amplitudeFrequency -> {
                Assert.assertEquals("C", amplitudeFrequency.getNote());
            });
        }

    }
}
