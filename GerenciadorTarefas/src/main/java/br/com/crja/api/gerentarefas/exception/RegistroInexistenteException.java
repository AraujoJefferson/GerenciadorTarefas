package br.com.crja.api.gerentarefas.exception;

public class RegistroInexistenteException extends RuntimeException {
    public RegistroInexistenteException() {
        super();
    }

    public RegistroInexistenteException(String message) {
        super(message);
    }

    public RegistroInexistenteException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegistroInexistenteException(Throwable cause) {
        super(cause);
    }
}
