package playtennis.enums;

public enum ClimaEnum {

    Ensolarado("Ensolarado", 0),
    Nublado("Nublado",1),
    Chuva("Chuva", 2);

    private final String key;
    private final Integer value;

    ClimaEnum(String key, Integer value) {
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
