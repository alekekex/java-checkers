public class Move {
    public String start;
    public String end;

    public Move(String end, String start) {
        this.end = end;
        this.start = start;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }
}
