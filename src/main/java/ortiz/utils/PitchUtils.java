package ortiz.utils;

public class PitchUtils {

    static String[] notes = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};

    public static String closestKey(double freq, boolean withRange) {
        int key = closestKeyIndex(freq);
        if (key <= 0) {
            return null;
        }
        int range = 1 + (key - 1) / notes.length;
        return notes[(key - 1) % notes.length] + (withRange ? range : "");
    }

    public static int closestKeyIndex(double freq) {
        return 1 + (int) ((12 * Math.log(freq / 440) / Math.log(2) + 49) - 0.5);
    }

}
