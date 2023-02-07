import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Put a short phrase describing the program here. A program that guesses the u
 * value in Jagers Formula
 *
 * @author Charan Nanduri
 *
 */
public final class ABCDGuesser1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ABCDGuesser1() {
    }

    /**
     * asks what constant u should be appoximated and then asks for 4 personal
     * numbers to then calculate 4 other numbers that bring the estimate close
     * to the u that is specified.
     */
    private static void myMethod(double w, double x, double y, double z,
            double constant) {
        /*
         *
         */
        double[] nums = { -5.0, -4.0, -3.0, -2.0, -1.0, -1.0 / 2.0, -1.0 / 3.0,
                -1.0 / 4.0, 0, 1.0 / 4.0, 1.0 / 3.0, 1.0 / 2.0, 1.0, 2.0, 3.0,
                4.0, 5.0 };
        int a = 0, b = 0, c = 0, d = 0;
        double ctr1 = 0, ctr2 = 0, ctr3 = 0, ctr4 = 0;
        double est, error;
        double formula = (Math.pow(w, nums[0]) * Math.pow(x, nums[0])
                * Math.pow(y, nums[0]) * Math.pow(z, nums[0]) - constant);
        while (a < 17) {
            while (b < 17) {
                while (c < 17) {
                    while (d < 17) {
                        est = (Math.pow(w, nums[a]) * Math.pow(x, nums[b])
                                * Math.pow(y, nums[c]) * Math.pow(z, nums[d])
                                - constant);
                        if (est < formula) {
                            formula = est;
                            ctr1 = nums[a];
                            ctr2 = nums[b];
                            ctr3 = nums[c];
                            ctr4 = nums[d];
                        }
                        d++;
                    }
                    c++;
                    b = 0;
                }
                b++;
                a = 0;
            }
            a++;
            c = 0;
        }
        error = (formula / c) * 100;
        System.out.print("Error : " + error);
        System.out.print("First exponent: " + ctr1);
        System.out.print("Second exponent: " + ctr2);
        System.out.print("Third exponent: " + ctr3);
        System.out.print("Fourth exponent: " + ctr4);

    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        String c;
        double number = 0;
        boolean status;
        out.print("Enter a constant that you would like to apporoximate: ");
        c = in.nextLine();
        status = FormatChecker.canParseDouble(c);
        while (status == false) {
            out.print(
                    "Error. Enter a constant that you would like to apporoximate: ");
            c = in.nextLine();
            status = FormatChecker.canParseDouble(c);
        }
        while (status == true) {
            number = Double.parseDouble(c);
            status = false;
        }
        return number;
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        String num;
        double number = 0;
        boolean status;
        out.print(
                "Enter a number, Only enter positive numbers that are not equal to 1: ");
        num = in.nextLine();
        status = FormatChecker.canParseDouble(num);
        while (status == false) {
            out.print(
                    "Enter a number, Only enter positive numbers that are not equal to 1: ");
            num = in.nextLine();
            status = FormatChecker.canParseDouble(num);
        }
        while (status == true) {
            number = Double.parseDouble(num);
            status = false;
        }
        return number;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Put your main program code here; it may call myMethod as shown
         */
        double w, x, y, z, c;

        c = getPositiveDouble(in, out);
        w = getPositiveDoubleNotOne(in, out);
        x = getPositiveDoubleNotOne(in, out);
        y = getPositiveDoubleNotOne(in, out);
        z = getPositiveDoubleNotOne(in, out);

        myMethod(w, x, y, z, c);

        /*
         * Close input and output streams
         */

        in.close();
        out.close();
    }

}
