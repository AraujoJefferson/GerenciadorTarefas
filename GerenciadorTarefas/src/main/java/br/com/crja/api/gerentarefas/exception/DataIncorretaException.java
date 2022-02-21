package br.com.crja.api.gerentarefas.exception;

public class DataIncorretaException extends RuntimeException {
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
