public class Position {
    private char column;
    private int row;

    public Position(String position) {
        this.column = position.charAt(0);
        this.row = position.charAt(1) - '0';
    }

    public int getColumn() {
        return column - 'A';
    }

    public int getRow() {
        return row - 1;
    }
}
