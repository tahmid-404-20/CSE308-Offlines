public abstract class DriveTrain {
    protected String driveTrainType;

    public String getDriveTrainType() {
        return driveTrainType;
    }
}

class AllWheelDriveTrain extends DriveTrain {
    public AllWheelDriveTrain() {
        driveTrainType = "All-wheels Drive Train";
    }
}

class RearWheelDriveTrain extends DriveTrain {
    public RearWheelDriveTrain() {
        driveTrainType = "Rear-wheel Drive Train";
    }
}