package util;

public enum ServerState {
    Operational("Operational"), PartiallyDown("Partially Down"),
    FullyDown("Fully Down");

    private String stateDescription;

    ServerState(String stateDescription) {
        this.stateDescription = stateDescription;
    }

    public String getStateDescription() {
        return stateDescription;
    }
}
