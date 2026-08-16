import java.util.ArrayList;
import java.util.List;

public class Board {
    private Piece[][] board;
    private List<Piece> whitePieces;
    private List<Piece> blackPieces;

    public Board() {
        this.board = new Piece[8][8];
        this.whitePieces = new ArrayList<>();
        this.blackPieces = new ArrayList<>();

        initializeBoard();
    }

    public Piece[][] getBoard() {
        return board;
    }

    public List<Piece> getWhitePieces() {
        return whitePieces;
    }

    public List<Piece> getBlackPieces() {
        return blackPieces;
    }

    public void initializeBoard() {
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                if (i > 4) {
                    if (i % 2 == j % 2) {
                        board[i][j] = new NormalPiece("BLACK");
                        blackPieces.add(board[i][j]);
                    }
                } else if (i < 3) {
                    if (i % 2 == j % 2) {
                        board[i][j] = new NormalPiece("WHITE");
                        whitePieces.add(board[i][j]);
                    }
                }
            }
        }
    }

    public Piece getPiece(Position position) {
        return board[position.getRow()][position.getColumn()];
    }

    public boolean isEmpty(Position position) {
        return board[position.getRow()][position.getColumn()] == null;
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
}
