package playtennis.enums;

public enum TemperaturaEnum {
    Quente("Quente", 0),
    Moderada("Moderada", 1),
    Fria("Fria", 2);

    private final String key;
    private final Integer value;

    TemperaturaEnum(String key, Integer value) {
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
