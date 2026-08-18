public class Player {
    public static final int INITIAL_PIECE_COUNT = 12;

    private String name;
    private String color;
    private int pieceCount;

    public Player(String name, String color) {
        this.name = name;
        this.color = color;
        this.pieceCount = INITIAL_PIECE_COUNT;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getPieceCount() {
        return pieceCount;
    }

    public void removePiece() {
        pieceCount--;
    }

    public boolean hasNoPieces() {
        return pieceCount == 0;
    }
}
