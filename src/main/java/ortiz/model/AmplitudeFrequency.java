package ortiz.model;

import ortiz.utils.PitchUtils;

public class AmplitudeFrequency {

    private Double amplitude;

    private Double frequency;

    private String note;

    private String noteWithOctave;

    public AmplitudeFrequency(Double amplitude, Double frequency) {
        this.amplitude = amplitude;
        this.frequency = frequency;
        this.note = PitchUtils.closestKey(frequency, false);
        this.noteWithOctave = PitchUtils.closestKey(frequency, true);
    }

    public Double getAmplitude() {
        return amplitude;
    }

    public Double getFrequency() {
        return frequency;
    }

    public String getNote() {
        return note;
    }

    public String getNoteWithOctave() {
        return noteWithOctave;
    }
}
