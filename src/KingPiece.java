public class KingPiece extends Piece implements Reversible {
    public KingPiece(String color) {
        super(color);
    }

    @Override
    public String getPiece() {
        if (getColor().equals("WHITE"))
            return "◎";
        else if (getColor().equals("BLACK"))
            return "◉";
        else return null;
    }

    @Override
    public boolean canMoveBackward() {
        return true;
    }
}
