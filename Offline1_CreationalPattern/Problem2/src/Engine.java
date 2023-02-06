public abstract class Engine {
    protected String type;

    public String getType() {
        return type;
    }
}

class HydrogenFuelCell extends Engine {
    public HydrogenFuelCell() {
        type = "Hydrogen Fuel Cell";
    }
}

class ElectricEngine extends Engine {
    public ElectricEngine() {
        type = "Electric Engine";
    }
}