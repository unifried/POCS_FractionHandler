// Import
import java.util.Scanner;

public class testFraction2 {
    // Private reference to classes
    private static Scanner inputHandler = new Scanner(System.in);
    private static Fraction baseFraction = new Fraction();
    private static Fraction modifierFraction = new Fraction();
    public static void entryPoint()
    {
        // Sets up the test
        runTestSetup();

        // Handles how long the test runs for (At least 1 addition is required)
        do runTest();
        while (runTestAgain());
    }

    private static void runTestSetup()
    {
        int iNumerator   = getNumberInput("Numerator", true);
        int iDenominator = getNumberInput("Denominator", false);

        baseFraction.setNumerator(iNumerator);
        baseFraction.setDenominator(iDenominator);
    }

    private static void runTest()
    {
        // Infrom the user
        System.out.println("Enter the details for the fraction that will be added to the base fraction below.");

        // Get the inputs
        int iNumerator   = getNumberInput("Numerator", true);
        int iDenominator = getNumberInput("Denominator", false);

        // Updates the modifier fraction class
        modifierFraction.setNumerator(iNumerator);
        modifierFraction.setDenominator(iDenominator);

        // Add the modifier to the base
        baseFraction.add(modifierFraction);

        // Output the new fraction
        System.out.print("The new fraction is: ");
        baseFraction.simpleFormattedStringOutput();
    }

    private static boolean runTestAgain()
    {
        // Prompt user
        System.out.println("Would you like to add another fraction? (Y/N):");

        // Clears input
        inputHandler.nextLine();

        // Get response
        char userResponse = inputHandler.nextLine().charAt(0);

        // Return response
        return (((userResponse == 'Y') || (userResponse == 'y')) ? true : false);
    }

    private static int getNumberInput(String fractionComponent, boolean canBeZero)
    {
        // Stores return integer, set to zero to ensure its always initialised
        int returnInteger = 0;

        boolean isValidReturn = false;
        while (!isValidReturn)
        {
            // Prompt user
            System.out.println("Enter a " + fractionComponent + ":");

            // Get input
            returnInteger = inputHandler.nextInt();

            // Check if input is valid, if it is end the loop else tell the user what is wrong and run again
            if (!canBeZero && (returnInteger == 0))
                System.out.println("A " + fractionComponent + " cannot be 0 for this input. Please choose a non-zero number when prompted again.");
            else
                isValidReturn = true;
        }

        return returnInteger;
    }
}
