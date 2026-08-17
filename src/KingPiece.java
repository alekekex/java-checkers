public class KingPiece extends Piece implements Reversible {
    public KingPiece(String color) {
        super(color);
    }

    @Override
    public String getPiece() {
        String piece = null;

        if (getColor().equals("WHITE"))
            piece = "◎";
        else if (getColor().equals("BLACK"))
            piece = "◉";

        return piece;
    }

    @Override
    public boolean canMoveBackward() {
        return true;
    }
}
