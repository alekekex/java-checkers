public class NormalPiece extends Piece {
    public NormalPiece(String color) {
        super(color);
    }

    @Override
    public String getPiece() {
        String piece = null;

        if (getColor().equals("WHITE"))
            piece = "○";
        else if (getColor().equals("BLACK"))
            piece = "●";

        return piece;
    }
}
