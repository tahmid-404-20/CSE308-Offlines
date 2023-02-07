public class SpaceShip {
    public static void simulateCrewMateWork(CrewMate crewMate) {
        System.out.println("Now, a crewmate will start work:");
        crewMate.study();
        crewMate.doMaintenanceWork();
        System.out.println();
    }

    public static void main(String[] args) {
        CrewMate crewMate1 = new CrewMates();
        CrewMate crewMate2 = new Adapter(new Imposters());

        simulateCrewMateWork(crewMate1);
        simulateCrewMateWork(crewMate2);
    }
}
