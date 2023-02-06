public class IntelCorei9Builder extends Builder {
    public IntelCorei9Builder() {
        this.pc = null;
    }

    @Override
    public void newPC() {
        this.pc = new PC("Intel Core i9 PC");
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
        this.pc.addFixedPart(new IntelCorei9_Gen11());
    }

    @Override
    public void addCooler() {
//        not applicable
    }

    @Override
    public void addDVDDrive() {
        this.pc.addFixedPart(new DVDDrive());
    }
}
