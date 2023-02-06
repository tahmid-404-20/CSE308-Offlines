public class GamingPCBuilder extends Builder{
    public GamingPCBuilder() {
        this.pc = null;
    }

    @Override
    public void newPC() {
        pc = new PC("Gaming PC");
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
        pc.addFixedPart(new AMDRyzen7_5700X());
    }

    @Override
    public void addCooler() {
//        not applicabe
    }

    @Override
    public void addDVDDrive() {
//        not applicable
}
}
