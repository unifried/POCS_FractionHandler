// Import
import java.util.Scanner;

public class Tester {
    // Private reference to classes
    private static Scanner inputHandler = new Scanner(System.in);

    // Program entry point
    public static void main(String[] args)
    {
        // Prompt the user
        System.out.println("Which test would you like to use? 1 or 2:");

        // Get response
        int testChoice = inputHandler.nextInt();

        // Call the corresponding function
        switch (testChoice)
        {
            case 1:
                testFraction1.entryPoint();
                break;
            case 2:
                testFraction2.entryPoint();
                break;
            default:
                System.out.println("Test does not exist. Program exited.");
                break;
        }
    }
}
