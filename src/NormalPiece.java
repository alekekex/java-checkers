public class NormalPiece extends Piece {
    public NormalPiece(String color) {
        super(color);
    }

    @Override
    public String getPiece() {
        if (getColor().equals("WHITE"))
            return "○";
        else if (getColor().equals("BLACK"))
            return "●";
        else return null;
    }
}
