package it.unicam.cs.asdl2223.mp2;

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
        stack.clear();
        if (s.isEmpty()) {
            return true;
        }
        if (!s.matches("^[\\s\\t\\n\\(\\)\\[\\]\\{\\}]*$")) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                stack.addFirst(s.charAt(i));
            } else if (s.charAt(i) == ')' || s.charAt(i) == ']' || s.charAt(i) == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                if (s.charAt(i) == ')' && stack.getFirst() == '(') {
                    stack.removeFirst();
                } else if (s.charAt(i) == ']' && stack.getFirst() == '[') {
                    stack.removeFirst();
                } else if (s.charAt(i) == '}' && stack.getFirst() == '{') {
                    stack.removeFirst();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

}
