public class Display {
    public static void displayIntro() {
        System.out.println("+----------------------------------+");
        System.out.println("|             CHECKERS             |");
        System.out.println("+----------------------------------+");
        System.out.println("| 1. Play Game                     |");
        System.out.println("| 2. Game Rules                    |");
        System.out.println("| 3. Exit Program                  |");
        System.out.println("+----------------------------------+");

    }

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

    public static void displayRules() {
        System.out.println("+----------------------------------+");
        System.out.println("|            GAME RULES            |");
        System.out.println("+----------------------------------+");
        System.out.println("| 1. Each player starts with 12    |");
        System.out.println("|    pieces.                       |");
        System.out.println("| 2. Pieces move diagonally        |");
        System.out.println("|    forward by one square.        |");
        System.out.println("| 3. Jump over an opponent's piece |");
        System.out.println("|    to capture it.                |");
        System.out.println("| 4. Kings can move forward and    |");
        System.out.println("|    backward.                     |");
        System.out.println("| 5. Capture all opponent pieces   |");
        System.out.println("|    to win the game.              |");
        System.out.println("+----------------------------------+");
    }

    public static void displayExit() {
        System.out.println("+----------------------------------+");
        System.out.println("|                                  |");
        System.out.println("|      Thank you for playing!      |");
        System.out.println("|          See you again!          |");
        System.out.println("|                                  |");
        System.out.println("+----------------------------------+");
    }
}
