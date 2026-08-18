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
        boolean isTurnOver = false;

        while (!isGameOver) {
            Player player = players[playerIdx];

            Display.displayBoard(board.getBoard());
            Move move = getMove(sc, player);

            if (makeMove(move, player)) { // has no king piece implementation yet
                checkIfGameOver(player);

                if (!isGameOver)
                    switchTurn();
            }
        }

        if (isGameOver) {
            System.out.println("We have a winner!"); // temp winner display
        }
    }

    public void initializePlayers(Scanner sc) {
        Display.displayPlayerSetup();
        String whiteName = Input.getName(sc, "Enter White player's name: ");
        String blackName = Input.getName(sc, "Enter Black player's name: ");

        players[0] = new Player(whiteName, "WHITE");
        players[1] = new Player(blackName, "BLACK");
    }

    public Move getMove(Scanner sc, Player player) {
        System.out.println(player.getName().toUpperCase() + "\'S MOVE (PLAYER " + (playerIdx + 1) + ")");
        Position start = Input.getPosition(sc, "From: ");
        Position end = Input.getPosition(sc, "To: ");

        return new Move(start, end);
    }

    public boolean isValidMove(Move move, Player player) {
        int startRow = move.getStart().getRow();
        int startColumn = move.getStart().getColumn();
        int endRow = move.getEnd().getRow();
        int endColumn = move.getEnd().getColumn();

        boolean isValid = false;

        if (!board.isEmpty(move.getStart()) && board.isEmpty(move.getEnd())) {
            if (player.getColor().equals("WHITE")) {
                if (endRow == startRow + 1 && endColumn == startColumn + 1 ||
                        endRow == startRow + 1 && endColumn == startColumn - 1)
                    isValid = true;
            } else if (player.getColor().equals("BLACK")) {
                if (endRow == startRow - 1 && endColumn == startColumn + 1 ||
                        endRow == startRow - 1 && endColumn == startColumn - 1)
                    isValid = true;
            }
        }

        return isValid;
    }

    public boolean isValidCapture(Move move, Player player) {
        int startRow = move.getStart().getRow();
        int startColumn = move.getStart().getColumn();
        int endRow = move.getEnd().getRow();
        int endColumn = move.getEnd().getColumn();

        Position captured = getCapturePosition(move);
        boolean isValid = false;

        if (!board.isEmpty(move.getStart()) && board.isEmpty(move.getEnd())) {
            if (player.getColor().equals("WHITE")) {
                if (endRow == startRow + 2 && endColumn == startColumn + 2 ||
                        endRow == startRow + 2 && endColumn == startColumn - 2)
                    if (!board.isEmpty(captured)) {
                        String capturedColor = board.getPiece(captured).getColor();

                        if (!capturedColor.equals(player.getColor()))
                            isValid = true;
                    }
            } else if (player.getColor().equals("BLACK")) {
                if (endRow == startRow - 2 && endColumn == startColumn + 2 ||
                        endRow == startRow - 2 && endColumn == startColumn - 2)
                    if (!board.isEmpty(captured)) {
                        String capturedColor = board.getPiece(captured).getColor();

                        if (!capturedColor.equals(player.getColor()))
                            isValid = true;
                    }
            }
        }

        return isValid;
    }

    public Position getCapturePosition(Move move) {
        int startRow = move.getStart().getRow();
        int startColumn = move.getStart().getColumn();
        int endRow = move.getEnd().getRow();
        int endColumn = move.getEnd().getColumn();

        int midRow = (startRow + endRow) / 2 + 1;
        int midColumn = (startColumn + endColumn) / 2;

        return new Position(midColumn, midRow);
    }

    public boolean makeMove(Move move, Player player) {
        boolean isValid = true;

        if (isValidMove(move, player))
            board.movePiece(move);
        else if (isValidCapture(move, player)) {
            board.capturePiece(move, getCapturePosition(move));
            player.removePiece();
        }
        else {
            isValid = false;
            System.out.println();
            System.out.println("Invalid move! Please try again.");
        }

        return isValid;
    }

    public void checkIfGameOver(Player player) {
        if (player.hasNoPieces())
            isGameOver = true;
    }

    public void switchTurn() {
        playerIdx = 1 - playerIdx;
    }
}
