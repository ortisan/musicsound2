package ortiz.model;

import ortiz.utils.Configs;

public class RawAudioMetadata {

    private byte[] payload;
    private int index;
    private Configs configs;

    public RawAudioMetadata(byte[] payload, int index, Configs configs) {
        this.payload = payload;
        this.index = index;
        this.configs = configs;
    }

    public byte[] getPayload() {
        return payload;
    }

    public int getIndex() {
        return index;
    }

    public Configs getConfigs() {
        return configs;
    }
}
