import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            Display.displayIntro();
            int choice = Input.getChoice(sc, "Enter your choice: ", 1, 3);
            System.out.println();

            switch (choice) {
                case 1:
                    Checkers game = new Checkers();
                    game.playGame(sc);
                    System.out.println();
                    break;
                case 2:
                    Display.displayRules();
                    Input.getEnter(sc, "Press Enter to return to Main Menu...");
                    System.out.println();
                    break;
                case 3:
                    isRunning = false;
                    Display.displayExit();
                    break;
            }
        }

        sc.close();
    }
}
