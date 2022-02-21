package br.com.crja.api.gerentarefas.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	private static final Logger loggerREH = LogManager.getLogger(RestExceptionHandler.class);

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		loggerREH.error("handleHttpMessageNotReadable");
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		loggerREH.error("handleMethodArgumentNotValid");
		BindingResult bindingResult = ex.getBindingResult();

		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY.value(), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { RegistroInexistenteException.class })
	protected ResponseEntity<?> handleDoesNotExist(Exception ex) {
		loggerREH.error(ex.getClass().getName());
		loggerREH.error(ex.getMessage());
		return new ResponseEntity<>(getError(ex, HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
	}

	private ErrorDetails getError(Exception ex, HttpStatus status) {
        ErrorDetails errorDetail = new ErrorDetails();
        errorDetail.setStatus(status.value());
        errorDetail.setDetail(ex.getMessage());
        errorDetail.setDeveloperMessage(ex.getClass().getName());

        return errorDetail;
    }
}