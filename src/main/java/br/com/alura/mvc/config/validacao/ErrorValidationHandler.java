package br.com.alura.mvc.config.validacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorValidationHandler {

	// PODEMOS TER MENSAGENS EM VARIOS IDIOMAS, NESSE CASO O SPRING AJUDA A GENTE COM ESSA CLASSE
	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	// AS ANOTAÇÕES DA BEAN VALIDATION LANÇAM ESSA EXCEPTION
	public List<ErrorInRquest> handle(MethodArgumentNotValidException exception) {

		List<ErrorInRquest> listResponseError = new ArrayList<>();

		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

		fieldErrors.forEach(e -> {
			// E O MÉTODO GETMESSAGE RECEBE A EXCEPTION E O LOCALE
			// LocaleContextHolder.getLocale VAI DE ACORDO COM O IDIOMA
			String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErrorInRquest error = new ErrorInRquest(e.getField(), message);
			listResponseError.add(error);
		});

		return listResponseError;
	}

}
