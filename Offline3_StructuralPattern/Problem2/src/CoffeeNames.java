public enum CoffeeNames {
    AMERICANO("Americano"), ESPRESSO("Espresso"), CAPPUCCINO("Cappuccino"), MOCHA("Mocha");

    private String name;

    private CoffeeNames(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
