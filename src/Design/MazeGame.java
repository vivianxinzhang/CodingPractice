package Design;

public class MazeGame {
    public void createGame() {
        // Call factory method makeRoom() for object creation, instead of
        // using constructors. In this way createGame() does not need to
        // know the details about what type of Room will be used, thus
        // can be directly reused by derived classes.
        Room room1 = makeRoom();
        Room room2 = makeRoom();

//        room1.connect(room2);
//        this.addRoom(room1);
//        this.addRoom(room2);
    }

    /** Return type is base class or interface */
    protected Room makeRoom() {
        return new OrdinaryRoom();  // handle room creation logic here
    }
}