public class Board {
    private Piece[][] board;

    public Board() {
        this.board = new Piece[8][8];

        initializeBoard();
    }

    public Piece[][] getBoard() {
        return board;
    }

    public void initializeBoard() {
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                if (i > 4) {
                    if (i % 2 == j % 2)
                        board[i][j] = new NormalPiece("BLACK");
                } else if (i < 3) {
                    if (i % 2 == j % 2)
                        board[i][j] = new NormalPiece("WHITE");
                }
            }
        }
    }

    public Piece getPiece(Position position) {
        return board[position.getRow()][position.getColumn()];
    }

    public boolean isLastRow(Position position) {
        return position.getRow() == 0 || position.getRow() == 7;
    }

    public boolean isEmpty(Position position) {
        return board[position.getRow()][position.getColumn()] == null;
    }

    public boolean isInBounds(Position position) {
        return position.getRow() >= 0 && position.getRow() < 8 &&
                position.getColumn() >= 0 && position.getColumn() < 8;
    }

    public void movePiece(Move move) {
        int startRow = move.getStart().getRow();
        int startColumn = move.getStart().getColumn();
        int endRow = move.getEnd().getRow();
        int endColumn = move.getEnd().getColumn();

        board[endRow][endColumn] = board[startRow][startColumn];
        board[startRow][startColumn] = null;
    }

    public void capturePiece(Move move, Position position) {
        int startRow = move.getStart().getRow();
        int startColumn = move.getStart().getColumn();
        int endRow = move.getEnd().getRow();
        int endColumn = move.getEnd().getColumn();
        int midRow = position.getRow();
        int midColumn = position.getColumn();

        board[endRow][endColumn] = board[startRow][startColumn];
        board[startRow][startColumn] = null;
        board[midRow][midColumn] = null;
    }

    public void promotePiece(Position position) {
        Piece piece = board[position.getRow()][position.getColumn()];
        board[position.getRow()][position.getColumn()] = new KingPiece(piece.getColor());
    }
}
