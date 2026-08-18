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

            /*if (isValidMove(move))
                move piece
            else if (isValidCapture(move))
                capture piece;
            else invalid*/


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
        int startRow = move.getStart().getRow();
        int startColumn = move.getStart().getColumn();
        int endRow = move.getEnd().getRow();
        int endColumn = move.getEnd().getColumn();

        String origColor = board.getPiece(move.getStart()).getColor();
        boolean isValid = false;

        if (!board.isEmpty(move.getStart()) && board.isEmpty(move.getEnd())) {
            if (origColor.equals("WHITE")) {
                if (endRow == startRow + 1 && endColumn == startColumn + 1 ||
                        endRow == startRow + 1 && endColumn == startColumn - 1)
                    isValid = true;
            } else if (origColor.equals("BLACK")) {
                if (endRow == startRow - 1 && endColumn == startColumn + 1 ||
                        endRow == startRow - 1 && endColumn == startColumn - 1)
                    isValid = true;
            }
        }

        return isValid;
    }

    public boolean isValidCapture(Move move) {
        int startRow = move.getStart().getRow();
        int startColumn = move.getStart().getColumn();
        int endRow = move.getEnd().getRow();
        int endColumn = move.getEnd().getColumn();

        int midRow = (startRow + endRow) / 2 + 1;
        int midColumn = (startColumn + endColumn) / 2;
        Position captured = new Position(midColumn, midRow);

        String origColor = board.getPiece(move.getStart()).getColor();
        boolean isValid = false;

        if (!board.isEmpty(move.getStart()) && board.isEmpty(move.getEnd())) {
            if (origColor.equals("WHITE")) {
                if (endRow == startRow + 2 && endColumn == startColumn + 2 ||
                        endRow == startRow + 2 && endColumn == startColumn - 2)
                    if (!board.isEmpty(captured)) {
                        String capturedColor = board.getPiece(captured).getColor();

                        if (!capturedColor.equals(origColor))
                            isValid = true;
                    }
            } else if (origColor.equals("BLACK")) {
                if (endRow == startRow - 2 && endColumn == startColumn + 2 ||
                        endRow == startRow - 2 && endColumn == startColumn - 2)
                    if (!board.isEmpty(captured)) {
                        String capturedColor = board.getPiece(captured).getColor();

                        if (!capturedColor.equals(origColor))
                            isValid = true;
                    }
            }
        }

        return isValid;
    }

    public void switchTurn() {
        playerIdx = 1 - playerIdx;
    }
}
