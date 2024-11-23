package Controller;

import Enums.DealerUpcard;
import Enums.HandType;

import java.util.Scanner;

public class ConsoleController {

    private static final Scanner input = new Scanner(System.in);

    // Print line divider
    public static void printDivider() {
        System.out.println("----------");
    }

    public static boolean printMainMenu() {
        boolean play = false;
        int prompt;

        printDivider();
        System.out.println("1: Start");
        System.out.println("2: Exit");
        printDivider();
        prompt = promptForInteger("What would you like to do? ");

        if (prompt == 1 ) {
            play = true;
        }

        return play;
    }

    // Prompt user for new input
    public static String promptForInput(String prompt) {
        System.out.print(prompt);
        return input.nextLine().trim();
    }

    // Prompt user for an integer input
    public static int promptForInteger(String prompt) {
        int value = 0;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            if (input.hasNextInt()) {
                value = input.nextInt();
                input.nextLine();
                valid = true;
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                input.next(); // Clear the invalid input
            }
        }
        return value;
    }

    // Prompt user to hit Enter to continue
    public static void promptForEnter() {
        System.out.println("Press ENTER to continue.");
        input.nextLine();
    }


}
