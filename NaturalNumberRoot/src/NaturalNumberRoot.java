//This GitHub code is for my (Charan's) personal use. do not copy this code or anything in this repository. I am not responsible
//for any resulting action by any governing body based upon the use of my (Charan's) personal repository.
import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program with implementation of {@code NaturalNumber} secondary operation
 * {@code root} implemented as static method.
 *
 * @author charan nanduri
 *
 */
public final class NaturalNumberRoot {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private NaturalNumberRoot() {
    }

    /**
     * Updates {@code n} to the {@code r}-th root of its incoming value.
     *
     * @param n
     *            the number whose root to compute
     * @param r
     *            root
     * @updates n
     * @requires r >= 2
     * @ensures n ^ (r) <= #n < (n + 1) ^ (r)
     */
    public static void root(NaturalNumber n, int r) {

        assert n != null : "Violation of: n is  not null";
        assert r >= 2 : "Violation of: r >= 2";
        NaturalNumber lowGuess = new NaturalNumber2(0); // set low guess equal to 0
        NaturalNumber highGuess = new NaturalNumber2(n); // set the high guess equal to n
        NaturalNumber guess = new NaturalNumber2(n); // set guess equal to n
        NaturalNumber one = new NaturalNumber2(1);
        // create number 1 as a type NaturalNumber
        NaturalNumber two = new NaturalNumber2(2);
        // create number 2 as a type NaturalNumber
        NaturalNumber dif = new NaturalNumber2(n); // set difference equal to n

        highGuess.increment(); // increase the high guess by 1,
        //(n+1 but using NaturalNumber method)
        guess.divide(two); // divide the guess by 2 n/2 but using a NaturalNumber method.

        if (n.compareTo(one) > 0)
        // if the given number n is greater than 0 this loop will run.
        {
            while (dif.compareTo(one) > 0)
            //while difference is greater than 0 this loop will continuously run.
            {
                NaturalNumber val = new NaturalNumber2(guess);
                //create new var 'val' that stores the guess.
                dif = new NaturalNumber2(); // resets difference

                val.power(r); //sets val equal to guess^(1/r)
                if (n.compareTo(val) >= 0)
                // if the given number n is greater
                //than or equal to val this loop will run.
                {
                    lowGuess.copyFrom(guess);
                    // sets low guess equal to guess if n is greater than or equal to val
                } else {
                    highGuess.copyFrom(guess);
                    // sets high guess equal to guess if n is greater than or equal to val
                }
                NaturalNumber low = new NaturalNumber2(lowGuess);
                // low equals lowGuess;
                NaturalNumber high = new NaturalNumber2(highGuess);
                // high equals highGuess;

                low.add(high);
                low.divide(two);
                guess.copyFrom(low);
                //we have now assigned the guess
                //value by using the interval halving method.

                dif.copyFrom(high);
                dif.subtract(lowGuess); //update dif for while loop to work

            }
            // root of n
            n.copyFrom(guess);
        }

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        final String[] numbers = { "0", "1", "13", "1024", "189943527", "0",
                "1", "13", "4096", "189943527", "0", "1", "13", "1024",
                "189943527", "82", "82", "82", "82", "82", "9", "27", "81",
                "243", "143489073", "2147483647", "2147483648",
                "9223372036854775807", "9223372036854775808",
                "618970019642690137449562111",
                "162259276829213363391578010288127",
                "170141183460469231731687303715884105727" };
        final int[] roots = { 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 15, 15, 15, 15, 15,
                2, 3, 4, 5, 15, 2, 3, 4, 5, 15, 2, 2, 3, 3, 4, 5, 6 };
        final String[] results = { "0", "1", "3", "32", "13782", "0", "1", "2",
                "16", "574", "0", "1", "1", "1", "3", "9", "4", "3", "2", "1",
                "3", "3", "3", "3", "3", "46340", "46340", "2097151", "2097152",
                "4987896", "2767208", "2353973" };

        for (int i = 0; i < numbers.length; i++) {
            NaturalNumber n = new NaturalNumber2(numbers[i]);
            NaturalNumber r = new NaturalNumber2(results[i]);
            root(n, roots[i]);
            if (n.equals(r)) {
                out.println("Test " + (i + 1) + " passed: root(" + numbers[i]
                        + ", " + roots[i] + ") = " + results[i]);
            } else {
                out.println("*** Test " + (i + 1) + " failed: root("
                        + numbers[i] + ", " + roots[i] + ") expected <"
                        + results[i] + "> but was <" + n + ">");
            }
        }

        out.close();
    }

=======
import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program with implementation of {@code NaturalNumber} secondary operation
 * {@code root} implemented as static method.
 *
 * @author charan nanduri
 *
 */
public final class NaturalNumberRoot {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private NaturalNumberRoot() {
    }

    /**
     * Updates {@code n} to the {@code r}-th root of its incoming value.
     *
     * @param n
     *            the number whose root to compute
     * @param r
     *            root
     * @updates n
     * @requires r >= 2
     * @ensures n ^ (r) <= #n < (n + 1) ^ (r)
     */
    public static void root(NaturalNumber n, int r) {

        assert n != null : "Violation of: n is  not null";
        assert r >= 2 : "Violation of: r >= 2";
        NaturalNumber lowGuess = new NaturalNumber2(0); // set low guess equal to 0
        NaturalNumber highGuess = new NaturalNumber2(n); // set the high guess equal to n
        NaturalNumber guess = new NaturalNumber2(n); // set guess equal to n
        NaturalNumber one = new NaturalNumber2(1);
        // create number 1 as a type NaturalNumber
        NaturalNumber two = new NaturalNumber2(2);
        // create number 2 as a type NaturalNumber
        NaturalNumber dif = new NaturalNumber2(n); // set difference equal to n

        highGuess.increment(); // increase the high guess by 1,
        //(n+1 but using NaturalNumber method)
        guess.divide(two); // divide the guess by 2 n/2 but using a NaturalNumber method.

        if (n.compareTo(one) > 0)
        // if the given number n is greater than 0 this loop will run.
        {
            while (dif.compareTo(one) > 0)
            //while difference is greater than 0 this loop will continuously run.
            {
                NaturalNumber val = new NaturalNumber2(guess);
                //create new var 'val' that stores the guess.
                dif = new NaturalNumber2(); // resets difference

                val.power(r); //sets val equal to guess^(1/r)
                if (n.compareTo(val) >= 0)
                // if the given number n is greater
                //than or equal to val this loop will run.
                {
                    lowGuess.copyFrom(guess);
                    // sets low guess equal to guess if n is greater than or equal to val
                } else {
                    highGuess.copyFrom(guess);
                    // sets high guess equal to guess if n is greater than or equal to val
                }
                NaturalNumber low = new NaturalNumber2(lowGuess);
                // low equals lowGuess;
                NaturalNumber high = new NaturalNumber2(highGuess);
                // high equals highGuess;

                low.add(high);
                low.divide(two);
                guess.copyFrom(low);
                //we have now assigned the guess
                //value by using the interval halving method.

                dif.copyFrom(high);
                dif.subtract(lowGuess); //update dif for while loop to work

            }
            // root of n
            n.copyFrom(guess);
        }

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        final String[] numbers = { "0", "1", "13", "1024", "189943527", "0",
                "1", "13", "4096", "189943527", "0", "1", "13", "1024",
                "189943527", "82", "82", "82", "82", "82", "9", "27", "81",
                "243", "143489073", "2147483647", "2147483648",
                "9223372036854775807", "9223372036854775808",
                "618970019642690137449562111",
                "162259276829213363391578010288127",
                "170141183460469231731687303715884105727" };
        final int[] roots = { 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 15, 15, 15, 15, 15,
                2, 3, 4, 5, 15, 2, 3, 4, 5, 15, 2, 2, 3, 3, 4, 5, 6 };
        final String[] results = { "0", "1", "3", "32", "13782", "0", "1", "2",
                "16", "574", "0", "1", "1", "1", "3", "9", "4", "3", "2", "1",
                "3", "3", "3", "3", "3", "46340", "46340", "2097151", "2097152",
                "4987896", "2767208", "2353973" };

        for (int i = 0; i < numbers.length; i++) {
            NaturalNumber n = new NaturalNumber2(numbers[i]);
            NaturalNumber r = new NaturalNumber2(results[i]);
            root(n, roots[i]);
            if (n.equals(r)) {
                out.println("Test " + (i + 1) + " passed: root(" + numbers[i]
                        + ", " + roots[i] + ") = " + results[i]);
            } else {
                out.println("*** Test " + (i + 1) + " failed: root("
                        + numbers[i] + ", " + roots[i] + ") expected <"
                        + results[i] + "> but was <" + n + ">");
            }
        }

        out.close();
    }

>>>>>>> branch 'main' of http://github.com/charannanduri/CSE2221.git
}