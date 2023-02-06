public abstract class Processor extends Parts{
    protected String model;

    @Override
    public String getDetails() {
        return "Processor -> Model: "+ model;
    }
}

class AMDRyzen7_5700X extends Processor {
    public AMDRyzen7_5700X() {
        model = "AMD Ryzen 7 5700X";
        setPrice(28000.0);
    }
}

class IntelCorei5_Gen11 extends Processor {
    public IntelCorei5_Gen11() {
        model = "Intel Core i5 11th Generation";
        setPrice(20000.0);
    }
}

class IntelCorei7_Gen11 extends Processor {
    public IntelCorei7_Gen11() {
        model = "Intel Core i7 11th Generation";
        setPrice(37000.0);
    }
}
class IntelCorei9_Gen11 extends Processor {
    public IntelCorei9_Gen11() {
        model = "Intel Core i9 11th Generation";
        setPrice(65000.0);
    }
}