public interface CrewMate {
    void study();
    void doMaintenanceWork();
}

class CrewMates implements CrewMate {

    @Override
    public void study() {
        System.out.println("Crewmate is studying");
    }

    @Override
    public void doMaintenanceWork() {
        System.out.println("Crewmate is doing maintenance work");
    }
}
