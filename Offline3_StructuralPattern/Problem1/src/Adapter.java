public class Adapter implements CrewMate{
    Imposter imposter;

    public Adapter(Imposter imposter) {
        this.imposter = imposter;
    }

    @Override
    public void study() {
        imposter.poisonCrewMate();
    }

    @Override
    public void doMaintenanceWork() {
        imposter.damageSpaceship();
    }
}
