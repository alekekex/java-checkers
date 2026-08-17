import java.util.Scanner;

public class Checkers {
    private Board board;
    private Player[] players;
    private int playerIdx;
    private boolean isGameOver;

    public Checkers() {
        this.board = new Board();
        this.players = new Player[2];
        this.playerIdx = 0;
        this.isGameOver = false;
    }

    public void playGame(Scanner sc) {
        initializePlayers(sc);

        while (!isGameOver) {
            Player player = players[playerIdx];

            Display.displayBoard(board.getBoard());
            Move move = getMove(sc, player);


            if (!isGameOver)
                switchTurn();
        }
    }

    public void initializePlayers(Scanner sc) {
        Display.displayPlayerSetup();
        String whiteName = Input.getName(sc, "Enter White player's name: ");
        String blackName = Input.getName(sc, "Enter Black player's name: ");

        players[0] = new Player(whiteName, board.getWhitePieces());
        players[1] = new Player(blackName, board.getBlackPieces());
    }

    public Move getMove(Scanner sc, Player player) {
        System.out.println(player.getName().toUpperCase() + "\'S MOVE (PLAYER " + (playerIdx + 1) + ")");
        Position start = Input.getPosition(sc, "From: ");
        Position end = Input.getPosition(sc, "To: ");

        return new Move(start, end);
    }

    public boolean isValidMove(Move move) {
        boolean isValid = false;

        // validation stuff

        return isValid;
    }

    public void switchTurn() {
        playerIdx = 1 - playerIdx;
    }
}
