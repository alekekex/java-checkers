public class Display {
    public static void displayBoard(Piece[][] board) {
        System.out.println();
        System.out.println("    A   B   C   D   E   F   G   H");
        System.out.println("  +---+---+---+---+---+---+---+---+");

        for (int i = 7; i >= 0; i--) {
            System.out.print((i + 1) + " ");

            for (int j = 0; j < 8; j++) {
                System.out.print("| ");

                if (board[i][j] != null)
                    System.out.print(board[i][j].getPiece());
                else System.out.print(" ");

                System.out.print(" ");
            }

            System.out.println("|");
            System.out.println("  +---+---+---+---+---+---+---+---+");
        }

        System.out.println();
    }
}
