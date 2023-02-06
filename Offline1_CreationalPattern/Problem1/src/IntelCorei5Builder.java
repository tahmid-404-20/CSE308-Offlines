public class IntelCorei5Builder extends Builder{

    public IntelCorei5Builder() {
        this.pc = null;
    }

    @Override
    public void newPC() {
        this.pc = new PC("Intel Core i5 PC");
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
        this.pc.addFixedPart(new IntelCorei5_Gen11());
    }

    @Override
    public void addCooler() {
        this.pc.addFixedPart(new CPUCooler());
    }

    @Override
    public void addDVDDrive() {
//      not applicable
    }
}
