public interface Imposter {
    void poisonCrewMate();
    void damageSpaceship();
}

class Imposters implements Imposter {

    @Override
    public void poisonCrewMate() {
        System.out.println("Imposter is trying to poison crewmates");
    }

    @Override
    public void damageSpaceship() {
        System.out.println("Imposter is trying to damage spaceship");
    }
}
