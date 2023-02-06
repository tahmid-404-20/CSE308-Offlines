public class Director {

    public boolean addRAM(Builder builder) {
        return builder.addRAM();
    }

    public boolean addGraphicsCard(Builder builder) {
        return builder.addGraphicsCard();
    }

    public PC getPC(Builder builder) {
        return builder.getPc();
    }

    public void buildPC(Builder builder) {
        builder.newPC();
        builder.addMotherBoard();
        builder.addHDD();
        builder.addProcessor();
        builder.addCooler();
        builder.addDVDDrive();
    }
}
