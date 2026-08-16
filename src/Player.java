import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Piece> pieces;

    public Player(String name, List<Piece> pieces) {
        this.name = name;
        this.pieces = pieces;
    }

    public String getName() {
        return name;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void addPiece(Piece piece) {
        pieces.add(piece);
    }

    public void removePiece(Piece piece) {
        pieces.remove(piece);
    }
}
