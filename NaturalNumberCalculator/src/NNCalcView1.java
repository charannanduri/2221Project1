import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import components.naturalnumber.NaturalNumber;

/**
 * View class.
 *
 * @author Charan
 */
public final class NNCalcView1 extends JFrame implements NNCalcView {

    /**
     * Controller object registered with this view to observe user-interaction
     * events.
     */
    private NNCalcController controller;

    /**
     * State of user interaction: last event "seen".
     */
    private enum State {
        /**
         * Last event was clear, enter, another operator, or digit entry, resp.
         */
        SAW_CLEAR, SAW_ENTER_OR_SWAP, SAW_OTHER_OP, SAW_DIGIT
    }

    /**
     * State variable to keep track of which event happened last; needed to
     * prepare for digit to be added to bottom operand.
     */
    private State currentState;

    /**
     * Text areas.
     */
    private final JTextArea tTop, tBottom;

    /**
     * Operator and related buttons.
     */
    private final JButton bClear, bSwap, bEnter, bAdd, bSubtract, bMultiply,
            bDivide, bPower, bRoot;

    /**
     * Digit entry buttons.
     */
    private final JButton[] bDigits;

    /**
     * Useful constants.
     */
    private static final int TEXT_AREA_HEIGHT = 3, TEXT_AREA_WIDTH = 10,
            DIGIT_BUTTONS = 10, MAIN_BUTTON_PANEL_GRID_ROWS = 4,
            MAIN_BUTTON_PANEL_GRID_COLUMNS = 4, SIDE_BUTTON_PANEL_GRID_ROWS = 4,
            SIDE_BUTTON_PANEL_GRID_COLUMNS = 1, CALC_GRID_ROWS = 3,
            CALC_GRID_COLUMNS = 1;

    /**
     * No argument constructor.
     */
    public NNCalcView1() {
        // Create the JFrame being extended

        /*
         * Call the JFrame (superclass) constructor with a String parameter to
         * name the window in its title bar
         */
        super("Charan's Natural Number Calculator");

        // Set up the GUI widgets --------------------------------------------

        /*
         * Set up initial state of GUI to behave like last event was "Clear";
         * currentState is not a GUI widget per se, but is needed to process
         * digit button events appropriately
         */
        this.currentState = State.SAW_CLEAR;

        // TODO: fill in rest of body, following outline in comments

        /*
         * Create widgets
         */
        //text entry
        this.tTop = new JTextArea("", TEXT_AREA_HEIGHT, TEXT_AREA_WIDTH);
        this.tBottom = new JTextArea("", TEXT_AREA_HEIGHT, TEXT_AREA_WIDTH);
        //this.inst = new JTextArea("", TEXT_AREA_HEIGHT, TEXT_AREA_WIDTH);
        //number buttons
        this.bDigits = new JButton[DIGIT_BUTTONS];
        this.bDigits[0] = new JButton("0");
        this.bDigits[1] = new JButton("1");
        this.bDigits[2] = new JButton("2");
        this.bDigits[3] = new JButton("3");
        this.bDigits[4] = new JButton("4");
        this.bDigits[5] = new JButton("5");
        this.bDigits[6] = new JButton("6");
        this.bDigits[7] = new JButton("7");
        this.bDigits[8] = new JButton("8");
        this.bDigits[9] = new JButton("9");
        // Function Buttons
        this.bAdd = new JButton("+");
        this.bSubtract = new JButton("-");
        this.bMultiply = new JButton("x");
        this.bDivide = new JButton("/");
        this.bPower = new JButton("x^n");
        this.bRoot = new JButton("root");
        this.bClear = new JButton("clear");
        this.bSwap = new JButton("switch");
        this.bEnter = new JButton("enter");

        // Set up the GUI widgets --------------------------------------------

        /*
         * Text areas should wrap lines, and should be read-only; they cannot be
         * edited because allowing keyboard entry would require checking whether
         * entries are digits, which we don't want to have to do
         */
        this.tTop.setEditable(false);
        this.tTop.setLineWrap(true);
        this.tTop.setWrapStyleWord(true);

        this.tBottom.setEditable(false);
        this.tBottom.setLineWrap(true);
        this.tBottom.setWrapStyleWord(true);

        //this.inst.setEditable(false);
        //this.inst.setLineWrap(true);
        //this.inst.setWrapStyleWord(true);

        /*
         * Initially, the following buttons should be disabled: divide (divisor
         * must not be 0) and root (root must be at least 2) -- hint: see the
         * JButton method setEnabled
         */
        this.bRoot.setEnabled(false);
        this.bDivide.setEnabled(false);

        /*
         * Create scroll panes for the text areas in case number is long enough
         * to require scrolling
         */

        JScrollPane scrollT = new JScrollPane(this.tTop);
        JScrollPane scrollB = new JScrollPane(this.tBottom);

        /*
         * Create main button panel
         */

        JPanel buttons = new JPanel(new GridLayout(MAIN_BUTTON_PANEL_GRID_ROWS,
                MAIN_BUTTON_PANEL_GRID_COLUMNS));

        /*
         * Add the buttons to the main button panel, from left to right and top
         * to bottom
         */
        buttons.add(this.bDigits[7]);
        buttons.add(this.bDigits[8]);
        buttons.add(this.bDigits[9]);
        buttons.add(this.bDivide);
        buttons.add(this.bDigits[4]);
        buttons.add(this.bDigits[5]);
        buttons.add(this.bDigits[6]);
        buttons.add(this.bMultiply);
        buttons.add(this.bDigits[1]);
        buttons.add(this.bDigits[2]);
        buttons.add(this.bDigits[3]);
        buttons.add(this.bSubtract);
        buttons.add(this.bDigits[0]);
        buttons.add(this.bRoot);
        buttons.add(this.bPower);
        buttons.add(this.bAdd);

        /*
         * Create side button panel
         */

        JPanel sideButtons = new JPanel(new GridLayout(
                SIDE_BUTTON_PANEL_GRID_ROWS, SIDE_BUTTON_PANEL_GRID_COLUMNS));

        /*
         * Add the buttons to the side button panel, from left to right and top
         * to bottom
         */

        sideButtons.add(this.bEnter);
        sideButtons.add(this.bClear);
        sideButtons.add(this.bSwap);

        /*
         * Create combined button panel organized using flow layout, which is
         * simple and does the right thing: sizes of nested panels are natural,
         * not necessarily equal as with grid layout
         *
         */
        JPanel combinedPanel = new JPanel(new FlowLayout());

        /*
         * Add the other two button panels to the combined button panel
         */

        combinedPanel.add(buttons);
        combinedPanel.add(sideButtons);

        /*
         * Organize main window
         */
        this.setLayout(new GridLayout(CALC_GRID_ROWS, CALC_GRID_COLUMNS));

        /*
         * Add scroll panes and button panel to main window, from left to right
         * and top to bottom
         */

        this.add(scrollT);
        this.add(scrollB);
        this.add(combinedPanel);

        // Set up the observers ----------------------------------------------

        /*
         * Register this object as the observer for all GUI events
         */
        this.bDigits[0].addActionListener(this);
        this.bDigits[1].addActionListener(this);
        this.bDigits[2].addActionListener(this);
        this.bDigits[3].addActionListener(this);
        this.bDigits[4].addActionListener(this);
        this.bDigits[5].addActionListener(this);
        this.bDigits[6].addActionListener(this);
        this.bDigits[7].addActionListener(this);
        this.bDigits[8].addActionListener(this);
        this.bDigits[9].addActionListener(this);

        this.bEnter.addActionListener(this);
        this.bClear.addActionListener(this);
        this.bSwap.addActionListener(this);
        this.bAdd.addActionListener(this);
        this.bSubtract.addActionListener(this);
        this.bMultiply.addActionListener(this);
        this.bDivide.addActionListener(this);
        this.bPower.addActionListener(this);
        this.bRoot.addActionListener(this);

        // Set up the main application window --------------------------------
        String instructions = "select #, enter, select 2nd #, select operation.";
        /*
         * Make sure the main window is appropriately sized, exits this program
         * on close, and becomes visible to the user
         */
        this.pack();
        this.tTop.setText("0");
        this.tBottom.setText("0");
        //this.inst.setText(instructions);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void registerObserver(NNCalcController controller) {

        // TODO: fill in body
        this.controller = controller;
    }

    @Override
    public void updateTopDisplay(NaturalNumber n) {

        // TODO: fill in body
        this.tTop.setText(n.toString());

    }

    @Override
    public void updateBottomDisplay(NaturalNumber n) {

        // TODO: fill in body
        this.tBottom.setText(n.toString());

    }

    @Override
    public void updateSubtractAllowed(boolean allowed) {

        // TODO: fill in body
        if (allowed) {
            this.bSubtract.setEnabled(true);
        } else {
            this.bSubtract.setEnabled(false);
        }

    }

    @Override
    public void updateDivideAllowed(boolean allowed) {

        // TODO: fill in body
        if (allowed) {
            this.bDivide.setEnabled(true);
        } else {
            this.bDivide.setEnabled(false);
        }

    }

    @Override
    public void updatePowerAllowed(boolean allowed) {

        // TODO: fill in body
        if (allowed) {
            this.bPower.setEnabled(true);
        } else {
            this.bPower.setEnabled(false);
        }

    }

    @Override
    public void updateRootAllowed(boolean allowed) {

        // TODO: fill in body
        if (allowed) {
            this.bRoot.setEnabled(true);
        } else {
            this.bRoot.setEnabled(false);
        }

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        /*
         * Set cursor to indicate computation on-going; this matters only if
         * processing the event might take a noticeable amount of time as seen
         * by the user
         */
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        /*
         * Determine which event has occurred that we are being notified of by
         * this callback; in this case, the source of the event (i.e, the widget
         * calling actionPerformed) is all we need because only buttons are
         * involved here, so the event must be a button press; in each case,
         * tell the controller to do whatever is needed to update the model and
         * to refresh the view
         */
        Object source = event.getSource();
        if (source == this.bClear) {
            this.controller.processClearEvent();
            this.currentState = State.SAW_CLEAR;
        } else if (source == this.bSwap) {
            this.controller.processSwapEvent();
            this.currentState = State.SAW_ENTER_OR_SWAP;
        } else if (source == this.bEnter) {
            this.controller.processEnterEvent();
            this.currentState = State.SAW_ENTER_OR_SWAP;
        } else if (source == this.bAdd) {
            this.controller.processAddEvent();
            this.currentState = State.SAW_OTHER_OP;
        } else if (source == this.bSubtract) {
            this.controller.processSubtractEvent();
            this.currentState = State.SAW_OTHER_OP;
        } else if (source == this.bMultiply) {
            this.controller.processMultiplyEvent();
            this.currentState = State.SAW_OTHER_OP;
        } else if (source == this.bDivide) {
            this.controller.processDivideEvent();
            this.currentState = State.SAW_OTHER_OP;
        } else if (source == this.bPower) {
            this.controller.processPowerEvent();
            this.currentState = State.SAW_OTHER_OP;
        } else if (source == this.bRoot) {
            this.controller.processRootEvent();
            this.currentState = State.SAW_OTHER_OP;
        } else {
            for (int i = 0; i < DIGIT_BUTTONS; i++) {
                if (source == this.bDigits[i]) {
                    switch (this.currentState) {
                        case SAW_ENTER_OR_SWAP:
                            this.controller.processClearEvent();
                            break;
                        case SAW_OTHER_OP:
                            this.controller.processEnterEvent();
                            this.controller.processClearEvent();
                            break;
                        default:
                            break;
                    }
                    this.controller.processAddNewDigitEvent(i);
                    this.currentState = State.SAW_DIGIT;
                    break;
                }
            }
        }
        /*
         * Set the cursor back to normal (because we changed it at the beginning
         * of the method body)
         */
        this.setCursor(Cursor.getDefaultCursor());
    }

}
