/**
 * 
 */

/**
 * 
 * Eccezione che segnala che un algoritmo di ordinamento ha commesso un errore.
 * 
 * @author Luca Tesei
 *
 */
public class SortingException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 7289728998235L;

    /**
     * 
     */
    public SortingException() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public SortingException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public SortingException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public SortingException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public SortingException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

}
