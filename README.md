# Music of Sound 2

Extract musical notes from sound and generate the partwise(Music XML).


## Theory

### AUDIO

**Sample**

PCM audio, whether it is input or output, consists of samples. A single sample represents the amplitude of one channel of sound at a certain point in time. A lot of individual samples are necessary to represent actual sound; for CD audio, 44100 samples are taken every second.

Samples can be of many different sizes, ranging from 8 bit to 64 bit precision. The specific format of each sample can also vary - they can be big endian byte integers, little endian byte integers, or floating point numbers.

Musically, the sample size determines the dynamic range. The dynamic range is the difference between the quietest and the loudest signal that can be resproduced.


**Frame**

A frame consists of exactly one sample per channel. If there is only one channel (Mono sound) a frame is simply a single sample. If the sound is stereo, each frame consists of two samples, etc.


Frame size

This is the size in bytes of each frame. This can vary a lot: if each sample
is 8 bits, and we’re handling mono sound, the frame size is one byte.
Similarly in 6 channel audio with 64 bit floating point samples, the frame size is 48 bytes (Num. channels * sample size / 8 bits)


Rate

PCM sound consists of a flow of sound frames. The sound rate controls how often the current frame is replaced. For example, a rate of 8000 Hz means that a new frame is played or captured 8000 times per second.


Data rate

This is the number of bytes, which must be recorded or provided per second at a certain frame size and rate.
8000 Hz mono sound with 8 bit (1 byte) samples has a data rate of 8000 * 1 * 1 = 8 kb/s or 64kbit/s. This is typically used for telephony.
At the other end of the scale, 96000 Hz, 6 channel sound with 64 bit (8 bytes) samples has a data rate of 96000 * 6 * 8 = 4608 kb/s (almost 5 MB sound data per second)

[Link: Digital Audio Terminology](https://larsimmisch.github.io/pyalsaaudio/terminology.html)

Given an example of 440.wav on resources folder, the AudioFormat information is:

```java
URL resource = TesteAnalisadorSom.class.getResource("/440.wav");
File file = new File(resource.toURI());
AudioInputStream aos = AudioSystem.getAudioInputStream(file);
System.out.println("aos.getFormat() = " + aos.getFormat());
```

```
encoding = {AudioFormat$Encoding@1006} "PCM_SIGNED" - PCM Signed
sampleRate = 44100.0 - 44100 samples per second
sampleSizeInBits = 16 - The value of this sample is representing by short number size of 2 bytes
channels = 2 - 8 bits for each chanel
frameSize = 4 - Sample Size * Channels - 16 * 2 = 32/8(bits) = 4bytes
frameRate = 44100.0
bigEndian = false - Is little endian
properties = null
```

[Link: Java Sound An Introduction](https://www.developer.com/java/other/article.php/1565671/Java-Sound-An-Introduction.htm)



#### Byte order and data representation in memory

Big endian refers to the order where the most significant bytes come first. This means that the bytes representing the largest values come first. Regular integers are printed this way. The number "1025" shows the numeral one first which represents "1000". This is a representation most comfortable to humans. This most significant value first is represented in bytes for computer memory representation. The number 1025 is represented in hex as 0x0401 where 0x0400 represents 1024 and 0x0001 represents the numeral 1. The sum is 1025. The most significant (larger value) byte is listed first in this big endian representation.
One can see that the word size is a factor as well as it determines how many bytes are used to represent the number.

[Link: Endianess](http://www.yolinux.com/TUTORIALS/Endian-Byte-Order.html)


### Fourier Theory

[Link: Fourier and Sum of Sines](http://sites.music.columbia.edu/cmc/MusicAndComputers/chapter3/03_03.php)


##### Window Size FFT

[Link: Window Size for FFT](http://support.ircam.fr/docs/AudioSculpt/3.0/co/Window%20Size.html)



[Link: Abstract](http://sites.music.columbia.edu/cmc/MusicAndComputers/)


IN DEVELOPMENT...