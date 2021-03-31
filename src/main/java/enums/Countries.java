package enums;

public enum Countries {
    UNITED_STATES("United States");

    private String value;

    Countries(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
