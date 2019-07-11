package ortiz.tests;

import org.junit.Test;
import ortiz.reactive.AudioProcessor;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class AudioProcessorTest {

    @Test
    public void testFrequencies() throws URISyntaxException, IOException, UnsupportedAudioFileException {
        URL resource = AudioProcessor.class.getResource("/440MonoToneGenerator.wav");
        File audioFile = new File(resource.toURI());
        AudioProcessor audioProcessor = new AudioProcessor(audioFile);
//        audioProcessor.process();

    }
}
