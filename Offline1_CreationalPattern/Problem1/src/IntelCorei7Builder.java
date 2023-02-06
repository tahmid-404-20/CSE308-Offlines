public class IntelCorei7Builder extends Builder {
    public IntelCorei7Builder() {
        this.pc = null;
    }

    @Override
    public void newPC() {
        this.pc = new PC("Intel Core i7 PC");
        pc.setBasePrice(70000.0);
    }

    @Override
    public void addMotherBoard() {
        pc.addBasePart(new MotherBoard());
    }

    @Override
    public void addHDD() {
        pc.addBasePart(new HDD_1TB());
    }

    @Override
    public void addProcessor() {
        this.pc.addFixedPart(new IntelCorei7_Gen11());
    }

    @Override
    public void addCooler() {
        this.pc.addFixedPart(new LiquidCooler());
    }

    @Override
    public void addDVDDrive() {
//      not applicable
    }
}
