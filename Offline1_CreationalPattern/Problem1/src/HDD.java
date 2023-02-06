public abstract class HDD extends Parts{
    protected String storageCapacity;

    @Override
    public String getDetails() {
        return "HDD -> Storage Capacity: " + storageCapacity;
    }
}

class HDD_1TB extends HDD{
    public HDD_1TB() {
        storageCapacity = "1 TB";
        setPrice(0.0);
    }
}