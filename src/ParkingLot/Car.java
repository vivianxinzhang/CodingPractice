package ParkingLot;

public class Car extends Vehicle {
    @Override
    public VehicleSize getSize() {
        return VehicleSize.Compact;
    }
}

//public class Car extends Vehicle {
//    public Car(String plate) {
//        this.plate = plate;
//    }
//
//    @Override
//    public VehicleSize getSize() {
//        return VehicleSize.COMPACT;
//    }
//}