import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Input {
    public static int getChoice(Scanner sc, String prompt, int min, int max) {
        boolean isValid = false;
        int choice = -1;

        do {
            try {
                System.out.print(prompt);
                choice = sc.nextInt();
                sc.nextLine();

                if (max == -1) {
                    if (choice < min)
                        System.out.println("Invalid option! Value must be at least " + min + ".");
                    else isValid = true;
                } else {
                    if (!(choice >= min && choice <= max))
                        System.out.println("Invalid option! Please enter a value between " +
                                min + " and " + max + ".");
                    else isValid = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
            }
        } while (!isValid);

        return choice;
    }

    public static String getName(Scanner sc, String prompt) {
        boolean isValid = false;
        String name;

        do {
            System.out.print(prompt);
            name = sc.nextLine();

            if (name.isEmpty() || name.isBlank())
                System.out.println("Invalid input! Please enter a valid name.");
            else isValid = true;
        } while (!isValid);

        return name;
    }

    public static Position getPosition(Scanner sc, String prompt) {
        boolean isValid = false;
        Position position = null;
        String s;

        do {
            isValid = false;
            System.out.print(prompt);
            s = sc.nextLine();

            if (s.isEmpty() || s.isBlank())
                System.out.println("Invalid input! Please enter a valid position.");
            else if (s.length() != 2)
                System.out.println("Invalid input! Position must contains 2 characters only.");
            else if (s.charAt(0) < 'A' || s.charAt(0) > 'H' ||
                    s.charAt(1) < '1' || s.charAt(1) > '8')
                System.out.println("Invalid input! Position must be between A1 and H8.");
            else {
                isValid = true;
                position = new Position(s);
            }
        } while (!isValid);

        return position;
    }

    public static void getEnter(Scanner sc, String prompt) {
        System.out.print(prompt);
        sc.nextLine();
    }
}
