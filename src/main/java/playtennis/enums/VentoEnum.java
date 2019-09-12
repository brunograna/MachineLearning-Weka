package playtennis.enums;

public enum VentoEnum {
    Forte("Forte", 0),
    Fraco("Fraco", 1);

    private final String key;
    private final Integer value;

    VentoEnum(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }
    public Integer getValue() {
        return value;
    }
}
