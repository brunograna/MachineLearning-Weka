package playtennis.enums;

public enum UmidadeEnum {
    Alta("Alta", 0),
    Normal("Normal", 1);

    private final String key;
    private final Integer value;

    UmidadeEnum(String key, Integer value) {
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
