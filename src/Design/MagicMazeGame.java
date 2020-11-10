package Design;

public class MagicMazeGame extends MazeGame {
    /** Override the makeRoom method in the base class */
    @Override
    public Room makeRoom() {
        // handle complicated magic room creation logic here
        return new MagicRoom();
    }

    public static void main() {
        MazeGame game1 = new MazeGame();
        game1.createGame();     // 2 ordinary rooms

        MazeGame game2 = new MagicMazeGame();
        // MagicMazeGame can directly use createGame() defined in base class
        game2.createGame();     // 2 magic rooms
    }
}
