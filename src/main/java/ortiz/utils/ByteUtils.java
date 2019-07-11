package ortiz.utils;

import java.util.Arrays;

public class ByteUtils {

    public static Integer toInt(byte[] payload, boolean bigEndian) {
        int length = payload.length;
        int result = 0;

        if (bigEndian) {
            for (int i = 0, idx = length - 1; i < length; i++, idx--) {
                result += payload[idx] << i * 8;
            }
        } else {
            for (int i = 0; i < length; i++) {
                result += payload[i] << i * 8;
            }
        }
        return result;
    }

    public static Double toDouble(byte[] payload, boolean bigEndian) {
        return toInt(payload, bigEndian).doubleValue();
    }

    public static double[] toDouble(byte[] payloads, boolean bigEndian, int numBytesPerSample) {
        double[] result = new double[payloads.length / numBytesPerSample];

        for (int i = 0, idxResult = 0; i < payloads.length; i = i + numBytesPerSample) {
            result[idxResult++] = toDouble(Arrays.copyOfRange(payloads, i, i + numBytesPerSample), bigEndian);
        }

        return result;
    }

}
