public class MotherBoard extends Parts{
    private String name;
    public MotherBoard() {
        name = "Mother Board";
        setPrice(0.0);
    }

    @Override
    public String getDetails() {
        return name;
    }
}
