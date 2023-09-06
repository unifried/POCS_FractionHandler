public class Fraction {
    // Private integers used for storing the fraction components
    private int numerator;
    private int denominator;

    // Constructors

    // Base sets the components to 0
    Fraction()
    {
        setNumerator(1);
        setDenominator(1);
    }

    // Override: sets the components to set values
    Fraction(int inputNumerator, int inputDenominator)
    {
        setNumerator(inputNumerator);
        setDenominator(inputDenominator);
    }

    // Override: copies values from another fraction
    Fraction(Fraction referenceFraction)
    {
        setNumerator(referenceFraction.getNumerator());
        setDenominator(referenceFraction.getDenominator());
    }

    // Support methods
    private int getAbsolute(int inputNumber)
    {
        // Early return inputNumber is the number is 0 or greater than 0
        boolean requireFlip = (inputNumber < 0);
        if (!requireFlip) return inputNumber;

        // Number is now assumed as negative, is flipped and returned
        return (inputNumber * -1);
    }

    private boolean isFractionNegative()
    {
        // Checks if either numerator or denominator is negative, returns true if they are or else false
        return ((numerator < 0) || (denominator < 0));
    }

    private void multiplyNumerator(int inputFactor)
    {
        int result = this.numerator * inputFactor;
        boolean success = setNumerator(result);

        // Can never fail at this stage
    }

    private void multiplyDenominator(int inputFactor)
    {
        int result = this.denominator * inputFactor;
        boolean success = setDenominator(result);

        // Can never fail at this stage, since both denominators cannot be zero the result can never be zero
    }

    private void multiplyFraction(int inputFactor)
    {
        multiplyNumerator(inputFactor);
        multiplyDenominator(inputFactor);
    }

    private int getSmallerComponent()
    {
        return ((numerator > denominator) ? numerator : denominator);
    }

    // Returns the greatest common multiple for two numbers
    private static int greatestCommonMultiple(int inputNumber1, int inputNumber2)
    {
        // Storage integer
        int storageInteger = 0;

        while ((inputNumber1 != 0) && (inputNumber2 != 0))
        {
            // Sets storage integer to inputNumber2 - this preserves inputNumber2's value from change
            storageInteger = inputNumber2;

            // Sets inputNumber2's value to the modulo of in1 % in2 - this ensures that the higher value is always inputNumber 1, this ends the loop when they are divisible without a remainder (thus a 0 result for input number 2)
            // When a zero value is set the loop ends, and this technically inputNumber1 is the only one returned, however both are covered for a case where inputNumber1 is the exit case
            inputNumber2   = (inputNumber1 % inputNumber2);

            /*
                EXTENDED EXPLANATION:

                Loop 0:
                    Begin - 1 & 2: 25 70
                    End   - 1 & 2: 70 25

                Loop 1:
                    Begin - 1 & 2: 70 25
                    End   - 1 & 2: 25 20

                Loop 2:
                    Begin - 1 & 2: 25 20
                    End   - 1 & 2: 20 5

                Loop 3:
                    Begin - 1 & 2: 20 5
                    End   - 1 & 2: 5 0
             */

            // inputNumber1 is now set to the storageInteger - inputNumber2's preserved value
            inputNumber1   = storageInteger;
        }

        // Returns the sum of the two (One of the two values is always 0)
        return (inputNumber1 + inputNumber2);
    }

    private void divideNumerator(int inputFactor)
    {
        int result = (this.numerator / inputFactor);
        boolean success = setNumerator(result);

        // Can never fail at this stage
    }

    private void divideDenominator(int inputFactor)
    {
        int result = (this.denominator / inputFactor);
        boolean success = setDenominator(result);

        // Can never fail at this stage, since both denominators cannot be zero the result can never be zero
    }

    private void divideBoth(int inputFactor)
    {
        divideNumerator(inputFactor);
        divideDenominator(inputFactor);
    }

    // Getters & Setters
    boolean setNumerator(int inputNumerator)
    {
        // Early return false (not set) if input is invalid (Blank for now)
        boolean isValid = true;
        if (!isValid) return false;

        // Input is assumed valid, and is now set & thus returns true (set)
        this.numerator = inputNumerator;
        return true;
    }

    boolean setDenominator(int inputDenominator)
    {
        // Early return false (not set) if input is less than or equal to 0
        boolean isValid = (inputDenominator != 0);
        if (!isValid) return false;

        // Input is assumed valid, and is now set & thus returns true (set)
        this.denominator = inputDenominator;
        return true;
    }

    int getNumerator() { return this.numerator; }
    int getDenominator() { return this.denominator; }

    int getABSNumerator() { return getAbsolute(this.numerator); }
    int getABSDenominator() { return getAbsolute(this.denominator); }




    // Week 3 Q1 Output method
    void simpleStringOutput()
    {
        String simpleStringOutputText = (this.numerator + "/" + this.denominator);
        System.out.println(simpleStringOutputText);
    }

    // Week 3 Q2 Output method
    void simpleFormattedStringOutput()
    {
        // Get required variables
        boolean isNegative  = isFractionNegative();
        int absNumerator    = getABSNumerator();
        int absDenominator  = getABSDenominator();

        // Output the formatted simple string
        String simpleStringOutputText = (((isNegative) ? "-" : "") + absNumerator + "/" + absDenominator);
        System.out.println(simpleStringOutputText);
    }

    // Week 4 Q1
    public Fraction add(final Fraction referenceFraction)
    {
        // Make a copy fraction
        Fraction inputFraction = new Fraction(referenceFraction);

        // Ensure the fractions have the same base - checks if they are the same first
        int baseDenominator   = this.getDenominator();
        int secondDenominator = inputFraction.getDenominator();

        if (baseDenominator != secondDenominator)
        {
            this.multiplyFraction(secondDenominator);
            inputFraction.multiplyFraction(baseDenominator);
        }

        // Fractions are now addable
        int newNumerator = this.getNumerator() + inputFraction.getNumerator();

        // Set new numerator - boolean return is ignored because no error can occur
        this.setNumerator(newNumerator);

        // Call the simplify function
        this.simplify();

        // Returns a reference to the caller - this allows chaining
        return this;
    }

    // Week 4 Q2
    private void simplify()
    {
        // Gets the greatest common multiple
        int greatestCommonMultiple = greatestCommonMultiple(this.numerator, this.denominator);

        // Updates the fractions components
        this.divideBoth(greatestCommonMultiple);
    }

    // Chain simplify of the simplify function
    private Fraction cSimplify()
    {
        // Gets the greatest common multiple
        int greatestCommonMultiple = greatestCommonMultiple(this.numerator, this.denominator);

        // Updates the fractions components
        this.divideBoth(greatestCommonMultiple);

        // Returns a reference to the caller - this allows chaining
        return this;
    }
}
