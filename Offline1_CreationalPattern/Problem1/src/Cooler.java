public abstract class Cooler extends Parts {
    protected String type;

    @Override
    public String getDetails() {
        return "Cooler: " + type;
    }
}

class CPUCooler extends Cooler {
    public CPUCooler() {
        type = "CPU Cooler";
        setPrice(36000.0);
    }
}

class LiquidCooler extends Cooler {
    public LiquidCooler() {
        type = "Liquid Cooler";
        setPrice(17000.0);
    }
}
