package ParkingLot;

public class ParkingSpot {
    private final  VehicleSize size;
    private Vehicle currentVehicle;


    public ParkingSpot(VehicleSize size) {
        this.size = size;
    }

    boolean fit(Vehicle v) {
        return currentVehicle == null && size.getSize() >= v.getSize().getSize();
    }

    /** record a vehicle is parked in by updating the currentVehicle field */
    void park(Vehicle v) {
        currentVehicle = v;
    }

    void leave() {
        currentVehicle = null;
    }

    Vehicle getVehicle() {
        return currentVehicle;
    }
}
