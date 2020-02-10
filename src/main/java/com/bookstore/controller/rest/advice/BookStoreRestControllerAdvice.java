package com.bookstore.controller.rest.advice;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bookstore.constants.ErrorConstants;
import com.bookstore.exception.BusinessException;
import com.bookstore.exception.ServiceException;
import com.bookstore.model.Error;
import com.bookstore.model.ErrorResponseTO;

/**
 * RestController Advice for handing the exceptions in Rest Controllers.
 * 
 * @author shivanimalhotra
 *
 */
@ControllerAdvice
public class BookStoreRestControllerAdvice {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookStoreRestControllerAdvice.class);

	/**
	 * MessageSource instance.
	 */
	@Autowired
	private MessageSource messageSource;

	/**
	 * Method to handle the exceptions where method arguments are not valid.
	 * 
	 * @param ex
	 *            - MethodArgumentNotValidException
	 * @return - ResponseEntity<ErrorResponseTO>
	 */
	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public final ResponseEntity<ErrorResponseTO> handleArgumentsNotValidException(MethodArgumentNotValidException ex) {
		ErrorResponseTO errorResponse = new ErrorResponseTO();
		errorResponse.setError(true);
		LOGGER.error("Please provide the valid values for arguments ", ex);
		List<Error> errorDetails = resolveBindingResultErrors(ex.getBindingResult());
		errorResponse.setErrorDetails(errorDetails);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

	}

	/**
	 * Method to send the list of errors object.
	 * 
	 * @param bindingResult
	 *            - BindingResult
	 * @return - List<Error>
	 */
	private List<Error> resolveBindingResultErrors(BindingResult bindingResult) {
		return bindingResult.getFieldErrors().stream().map(fieldError -> new Error(fieldError.getDefaultMessage(), messageSource.getMessage(fieldError.getDefaultMessage(), null, Locale.getDefault())))
				.collect(Collectors.toList());
	}

	/**
	 * Method to handle the exceptions where arguments validations fails.
	 * 
	 * @param businessException
	 *            - BusinessException
	 * @return - ResponseEntity<ErrorResponseTO>
	 */
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorResponseTO> handleValidationException(BusinessException businessException) {

		LOGGER.error("Exception occured while serving the request with message: {} and error code message : {}", businessException.getUserMessage(),
				messageSource.getMessage(businessException.getErrorCodeEnum().getDescription(), null, Locale.getDefault()));
		ErrorResponseTO errorResponse = new ErrorResponseTO();
		List<Error> errors = new ArrayList<>();
		if (null != businessException.getBindingResult())
			errors = resolveBindingResultErrors(businessException.getBindingResult());

		errorResponse.setErrorDetails(errors);
		return new ResponseEntity<>(errorResponse, businessException.getErrorCodeEnum().getHttpStatus());
	}

	/**
	 * Method to handle any ServiceException.
	 * 
	 * @param serviceException
	 *            - ServiceException
	 * @return - ResponseEntity<ErrorResponseTO>
	 */
	@ExceptionHandler(value = { ServiceException.class })
	public ResponseEntity<ErrorResponseTO> handleServiceException(ServiceException serviceException) {
		LOGGER.error("Exception occured while serving the request", serviceException.getUserMessage(), serviceException);
		ErrorResponseTO errorResponse = new ErrorResponseTO();
		List<Error> errorList = new ArrayList<>();
		String code = messageSource.getMessage(ErrorConstants.GENERIC_ERROR_CODE, null, Locale.getDefault());
		String descrption = messageSource.getMessage(ErrorConstants.GENERIC_ERROR_MESSAGE, null, Locale.getDefault());
		Error error = new Error(code, descrption);
		errorList.add(error);
		errorResponse.setErrorDetails(errorList);
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Method to handle any technical exception.
	 * 
	 * @param exception
	 *            - Exception
	 * @return - ResponseEntity<ErrorResponseTO>
	 */
	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<ErrorResponseTO> handleServiceException(Exception exception) {
		LOGGER.error("Exception occured while serving the request", exception.getMessage(), exception);
		ErrorResponseTO errorResponse = new ErrorResponseTO();
		List<Error> errorList = new ArrayList<>();
		String code = messageSource.getMessage(ErrorConstants.GENERIC_ERROR_CODE, null, Locale.getDefault());
		String descrption = messageSource.getMessage(ErrorConstants.GENERIC_ERROR_MESSAGE, null, Locale.getDefault());
		Error error = new Error(code, descrption);
		errorList.add(error);
		errorResponse.setErrorDetails(errorList);
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
