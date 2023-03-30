import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Charan
 *
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static NaturalNumber evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";

        NaturalNumber val = new NaturalNumber2(0);
        NaturalNumber left = new NaturalNumber2(0);
        NaturalNumber right = new NaturalNumber2(0);
        if (exp.numberOfChildren() == 0) {
            val = new NaturalNumber1L(exp.attributeValue("value"));
        } else {
            left = evaluate(exp.child(0));
            right = evaluate(exp.child(1));
            if (exp.label().equals("plus")) {
                if (!right.isZero()) {
                    val.copyFrom(left);
                    val.add(right);
                } else {
                    Reporter.fatalErrorToConsole(
                            "Violates condition for addition command");
                }
            } else if (exp.label().equals("minus")) {
                if (!right.isZero()) {
                    val.copyFrom(left);
                    val.subtract(right);
                } else {
                    Reporter.fatalErrorToConsole(
                            "Violates condition for subtraction command");
                }
            } else if (exp.label().equals("times")) {
                if (!right.isZero()) {
                    val.copyFrom(left);
                    val.multiply(right);
                } else {
                    Reporter.fatalErrorToConsole(
                            "Violates condition for multiplicationcommand");
                }

            } else if (exp.label().equals("divide")) {
                if (!right.isZero()) {
                    val.copyFrom(left);
                    val.divide(right);
                } else {
                    Reporter.fatalErrorToConsole(
                            "Violates condition for division command");
                }
            }
        }
        /*
         * This line added just to make the program compilable. Should be
         * replaced with appropriate return statement.
         */
        return val;
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

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}
