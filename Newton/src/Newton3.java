import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here. calculates the sqaure root of
 * x.
 *
 * @author charan
 * @return square root estimate
 *
 */

public final class Newton3 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton3() {
    }

    /**
     * calculates the square root of x when x is greater than 0
     */

    private static double sqrt(double x, double EPSILON) {
        double r;
        if (x <= 0) {
            return 0;
        }
        r = x;
        while (Math.abs(r - x / r) > r * EPSILON) {
            r = (x / r + r) / 2.0;
        }
        return r;

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
        String resp = "y";
        double n = 0.0;
        double e = 0.0;
        while (('y' == resp.charAt(0)) && resp.length() == 1) {
            out.print("Enter a number: ");
            n = in.nextDouble();
            out.print("Enter Epsilon value: ");
            e = in.nextDouble();
            out.print("Square root: " + sqrt(n, e));
            out.print("Keep going?: ");
            in.nextLine();
        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}