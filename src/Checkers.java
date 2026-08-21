import java.util.ArrayList;
import java.util.List;
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
            Player opponent = players[1 - playerIdx];

            Display.displayBoard(board.getBoard());
            Move move = getMove(sc, player);

            if (makeMove(sc, move, player, opponent)) {
                checkIfGameOver(player);

                if (!isGameOver)
                    switchTurn();
            }
        }

        if (isGameOver) {
            Player winner = players[playerIdx];
            System.out.println(winner.getName() + " is the winner!");
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
            if (board.getPiece(move.getStart()) instanceof KingPiece) {
                if (endRow == startRow + 1 && endColumn == startColumn + 1 ||
                        endRow == startRow + 1 && endColumn == startColumn - 1 ||
                        endRow == startRow - 1 && endColumn == startColumn + 1 ||
                        endRow == startRow - 1 && endColumn == startColumn - 1)
                    isValid = true;
            } else if (player.getColor().equals("WHITE")) {
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
            if (board.getPiece(move.getStart()) instanceof KingPiece) {
                if (endRow == startRow + 2 && endColumn == startColumn + 2 ||
                        endRow == startRow + 2 && endColumn == startColumn - 2 ||
                        endRow == startRow - 2 && endColumn == startColumn + 2 ||
                        endRow == startRow - 2 && endColumn == startColumn - 2)
                    isValid = true;
            } else if (player.getColor().equals("WHITE")) {
                if (endRow == startRow + 2 && endColumn == startColumn + 2 ||
                        endRow == startRow + 2 && endColumn == startColumn - 2)
                    isValid = true;

            } else if (player.getColor().equals("BLACK")) {
                if (endRow == startRow - 2 && endColumn == startColumn + 2 ||
                        endRow == startRow - 2 && endColumn == startColumn - 2)
                    isValid = true;
            }

            if (isValid && !board.isEmpty(captured)) {
                String capturedColor = board.getPiece(captured).getColor();

                if (!capturedColor.equals(player.getColor()))
                    isValid = true;
                else isValid = false;
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

    public List<Position> getAvailableCaptures(Position position, Player player) {
        List<Position> positions = new ArrayList<>();
        List<Position> candidates = new ArrayList<>();

        candidates.add(new Position(position.getColumn() + 2, position.getRow() + 3));
        candidates.add(new Position(position.getColumn() - 2, position.getRow() + 3));
        candidates.add(new Position(position.getColumn() + 2, position.getRow() - 1));
        candidates.add(new Position(position.getColumn() - 2, position.getRow() - 1));

        for (Position candidate : candidates) {
            Move move = new Move(position, candidate);

            if (board.isInBounds(candidate) && isValidCapture(move, player))
                positions.add(candidate);
        }

        return positions;
    }

    public void continueCapture(Scanner sc, Position position, Player player) {
        List<Position> captures = getAvailableCaptures(position, player);

        if (captures.isEmpty()) {
            System.out.println();
            System.out.println("Successfully captured pieces!");
        } else if (captures.size() == 1) {
            Position capture = captures.get(0);
            Move move = new Move(position, capture);

            board.capturePiece(move, getCapturePosition(move));
            checkPromotion(move);

            continueCapture(sc, capture, player);
        } else {
            for (int i = 0; i < captures.size(); i++) {
                char column = (char) (captures.get(i).getColumn() + 'A');
                int row = captures.get(i).getRow() + 1;

                System.out.println((i + 1) + ". " + column + row);
            }

            int choice = Input.getChoice(sc, "Enter your choice: ", 1, captures.size());
            System.out.println();

            Position capture = captures.get(choice - 1);
            Move move = new Move(position, capture);

            board.capturePiece(move, getCapturePosition(move));
            checkPromotion(move);

            continueCapture(sc, capture, player);
        }
    }

    public boolean makeMove(Scanner sc, Move move, Player player, Player opponent) {
        boolean isValid = true;

        if (isValidMove(move, player)) {
            board.movePiece(move);
            checkPromotion(move);
        }
        else if (isValidCapture(move, player)) {
            board.capturePiece(move, getCapturePosition(move));
            opponent.removePiece();
            checkPromotion(move);

            continueCapture(sc, move.getEnd(), player);
        } else {
            isValid = false;
            System.out.println();
            System.out.println("Invalid move! Please try again.");
        }

        return isValid;
    }

    public void checkPromotion(Move move) {
        if (!board.isEmpty(move.getEnd()) && board.isLastRow(move.getEnd())) {
            board.promotePiece(move.getEnd());
            System.out.println();
            System.out.println("Piece promoted to King!");
        }
    }

    public void checkIfGameOver(Player player) {
        if (player.hasNoPieces())
            isGameOver = true;
    }

    public void switchTurn() {
        playerIdx = 1 - playerIdx;
    }
}
