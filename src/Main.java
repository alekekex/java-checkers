import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            Display.displayIntro();
            int choice = Input.getIntChoice(sc, "Enter your choice: ", 1, 3);

            switch (choice) {
                case 1:
                    System.out.println("Gameplay will be implemented soon!");
                    break;
                case 2:
                    Display.displayRules();
                    Input.getEnterInput(sc, "Press Enter to go back...");
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
