public abstract class Car {
    protected String carCompany;
    protected String color;
    protected String manufacturingCountry;
    protected Engine engine;
    protected DriveTrain driveTrain;

    public void printDetails() {
        System.out.println("Car company: " + carCompany);
        System.out.println("Color: " + color);
        System.out.println("Manufacturing Country: " + manufacturingCountry);
        System.out.println("Engine Type: " + engine.getType());
        System.out.println("Drive Train System: " + driveTrain.getDriveTrainType());
    }
}

class Toyota extends Car {
    public Toyota() {
        carCompany = "Toyota";
        color = "Red";
        manufacturingCountry = "Japan";
        engine = new HydrogenFuelCell();
        driveTrain = new RearWheelDriveTrain();
    }
}

class BMW extends Car {
    public BMW() {
        carCompany = "BMW";
        color = "Black";
        manufacturingCountry = "Germany";
        engine = new ElectricEngine();
        driveTrain = new RearWheelDriveTrain();
    }
}

class Tesla extends Car {
    public Tesla() {
        carCompany = "Tesla";
        color = "White";
        manufacturingCountry = "US";
        engine = new ElectricEngine();
        driveTrain = new AllWheelDriveTrain();
    }
}
