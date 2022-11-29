/**
 * An object of this class is an actor that uses an ASDL2223Deque<Character> as
 * a Stack in order to check that a sequence containing the following
 * characters: '(', ')', '[', ']', '{', '}' in any order is a string of balanced
 * parentheses or not. The input is given as a String in which white spaces,
 * tabs and newlines are ignored.
 * <p>
 * Some examples:
 * <p>
 * - " (( [( {\t (\t) [ ] } ) \n ] ) ) " is a string o balanced parentheses - " " is a
 * string of balanced parentheses - "(([)])" is NOT a string of balanced
 * parentheses - "( { } " is NOT a string of balanced parentheses - "}(([]))" is
 * NOT a string of balanced parentheses - "( ( \n [(P)] \t ))" is NOT a string
 * of balanced parentheses
 *
 * @author Template: Luca Tesei, Implementation: Matteo Machella - matteo.machella@studenti.unicam.it
 */
public class BalancedParenthesesChecker {

    // The stack is to be used to check the balanced parentheses
    private ASDL2223Deque<Character> stack;

    /**
     * Create a new checker.
     */
    public BalancedParenthesesChecker() {
        this.stack = new ASDL2223Deque<Character>();
    }

    /**
     * Check if a given string contains a balanced parentheses sequence of
     * characters '(', ')', '[', ']', '{', '}' by ignoring white spaces ' ',
     * tabs '\t' and newlines '\n'.
     *
     * @param s the string to check
     * @return true if s contains a balanced parentheses sequence, false
     * otherwise
     * @throws IllegalArgumentException if s contains at least a character
     *                                  different form:'(', ')', '[', ']',
     *                                  '{', '}', white space ' ', tab '\t'
     *                                  and newline '\n'
     */
    public boolean check(String s) {
        // First of all the stack is cleared
        stack.clear();
        // Check if s is null
        if (s.isEmpty()) {
            return true;
        }
        /*
         * Check if s contains only the characters '(', ')', '[', ']', '{', '}',
         * white space ' ', tab '\t' and newline '\n'
         */
        if (!s.matches("^[\\s\\t\\n\\(\\)\\[\\]\\{\\}]*$")) {
            throw new IllegalArgumentException();
        }
        /*
         * Check if s contains a balanced parentheses sequence
         */
        for (int i = 0; i < s.length(); i++) {
            /*
             * If the character is a white space, a tab or a newline, it is
             * ignored
             */
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                stack.addFirst(s.charAt(i));
                /*
                 * If the character is a closing parentheses, it is checked if
                 * it is the closing parentheses of the last opening parentheses
                 * in the stack
                 */
            } else if (s.charAt(i) == ')' || s.charAt(i) == ']' || s.charAt(i) == '}') {
                // If the stack is empty, the string is not balanced
                if (stack.isEmpty()) {
                    return false;
                }
                if (s.charAt(i) == ')' && stack.getFirst() == '(') {
                    /*
                     * If the character is the closing parentheses of the last
                     * opening parentheses in the stack, it is removed from the
                     * stack
                     */
                    stack.removeFirst();
                } else if (s.charAt(i) == ']' && stack.getFirst() == '[') {
                    stack.removeFirst();
                } else if (s.charAt(i) == '}' && stack.getFirst() == '{') {
                    stack.removeFirst();
                } else {
                    /*
                     * If the character is not the closing parentheses of the
                     * last opening parentheses in the stack, the string is not
                     * balanced
                     */
                    return false;
                }
            }
        }
        /*
         * If the stack is empty, the string is balanced, otherwise it is not
         * balanced
         */
        return stack.isEmpty();
    }

}
