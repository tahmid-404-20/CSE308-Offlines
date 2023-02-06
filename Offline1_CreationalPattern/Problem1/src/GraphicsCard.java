public class GraphicsCard extends Parts{
    protected int storageInGB;

    @Override
    public String getDetails() {
        return "Graphics Card -> Storage: " + storageInGB + " GB";
    }
}

class GraphicsCard2GB extends GraphicsCard {
    public GraphicsCard2GB() {
        storageInGB = 2;
        setPrice(6500.0);
    }
}

class GraphicsCard4GB extends GraphicsCard {
    public GraphicsCard4GB() {
        storageInGB = 4;
        setPrice(7600.0);
    }
}