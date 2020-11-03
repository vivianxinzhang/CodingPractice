package ParkingLot;
import java.util.*;

class Level {
    private final List<ParkingSpot> spots;

    Level(int numOfSpots) {
        List<ParkingSpot> list = new ArrayList<>(numOfSpots);
        int i = 0;
        for (; i < numOfSpots / 2; i++) {
            list.add(new ParkingSpot(VehicleSize.Compact));
        }
        for (; i < numOfSpots; i++) {
            list.add(new ParkingSpot(VehicleSize.Large));
        }
        spots = Collections.unmodifiableList(list);
    }

    boolean hasSpot(Vehicle v) {
        for (ParkingSpot s : spots) {
            if (s.fit(v)) {
                return true;
            }
        }
        return false;
    }

    boolean park(Vehicle v) {
        for (ParkingSpot s : spots) {
            if (s.fit(v)) {
                s.park(v);
                return true;
            }
        }
        return false;
    }

    boolean leave(Vehicle v) {
        for (ParkingSpot s : spots) {
            if (s.getVehicle() == v) {
                s.leave();
                return true;
            }
        }
        return false;
    }
}
