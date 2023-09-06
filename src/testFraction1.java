import java.util.Scanner;

public class testFraction1 {
    // Private reference to classes
    private static Scanner inputHandler = new Scanner(System.in);
    private static Fraction testFraction = new Fraction();

    // Program entry point
    public static void entryPoint()
    {
        runTest();
    }

    // Run test loop
    private static void runTest()
    {
        // Update fraction
        updateFraction();

        // Output via both methods
        testFraction.simpleStringOutput();
        testFraction.simpleFormattedStringOutput();
    }

    // Update fraction
    private static void updateFraction()
    {
        int iNumerator   = getNumberInput("Numerator", true);
        int iDenominator = getNumberInput("Denominator", false);

        testFraction.setNumerator(iNumerator);
        testFraction.setDenominator(iDenominator);
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
