package it.unicam.cs.asdl2223.mp2;

/**
 * An object of this class is an actor that uses an ASDL2223Deque<Character> as
 * a Stack in order to check that a sequence containing the following
 * characters: '(', ')', '[', ']', '{', '}' in any order is a string of balanced
 * parentheses or not. The input is given as a String in which white spaces,
 * tabs and newlines are ignored.
 * 
 * Some examples:
 * 
 * - " (( [( {\t (\t) [ ] } ) \n ] ) ) " is a string o balanced parentheses - " " is a
 * string of balanced parentheses - "(([)])" is NOT a string of balanced
 * parentheses - "( { } " is NOT a string of balanced parentheses - "}(([]))" is
 * NOT a string of balanced parentheses - "( ( \n [(P)] \t ))" is NOT a string
 * of balanced parentheses
 * 
 * @author Template: Luca Tesei, Implementation: INSERIRE NOME E COGNOME DELLO
 *         STUDENTE - INSERIRE ANCHE L'EMAIL xxxx@studenti.unicam.it
 *
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
     * @param s
     *              the string to check
     * @return true if s contains a balanced parentheses sequence, false
     *         otherwise
     * @throws IllegalArgumentException
     *                                      if s contains at least a character
     *                                      different form:'(', ')', '[', ']',
     *                                      '{', '}', white space ' ', tab '\t'
     *                                      and newline '\n'
     */
    public boolean check(String s) {
        // TODO implement - NOTE: the stack must be cleared first every time
        // this method is called. Then it has to be used to perform the check.
        return false;
    }

}
