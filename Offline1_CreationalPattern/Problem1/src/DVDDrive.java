public class DVDDrive extends Parts {
    private String name;
    public DVDDrive() {
        name  = "DVD Drive";
        setPrice(6000.0);
    }

    @Override
    public String getDetails() {
        return "Drive: " + name;
    }
}