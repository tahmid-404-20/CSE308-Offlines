public class RAMDDR4 extends Parts{
    protected int storageCapacityInGB;
    protected int clockRateinMHz;

    @Override
    public String getDetails() {
        return "DDR4 RAM -> Storage: " + storageCapacityInGB + " GB Clock Rate: " + clockRateinMHz + " MHz";
    }
}

class RAMDDR4_2666MHz extends RAMDDR4 {
    public RAMDDR4_2666MHz() {
        storageCapacityInGB = 8;
        clockRateinMHz = 2666;
        setPrice(2620.0);
    }
}

class RAMDDR4_3200MHz extends RAMDDR4 {
    public RAMDDR4_3200MHz() {
        storageCapacityInGB = 8;
        clockRateinMHz = 3200;
        setPrice(2950.0);
    }
}
