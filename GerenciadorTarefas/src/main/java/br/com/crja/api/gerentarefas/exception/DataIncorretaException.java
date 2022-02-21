package br.com.crja.api.gerentarefas.exception;

public class DataIncorretaException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -9197448141185454468L;

	public DataIncorretaException() {
        super();
    }

    public DataIncorretaException(String message) {
        super(message);
    }

    public DataIncorretaException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataIncorretaException(Throwable cause) {
        super(cause);
    }
}
