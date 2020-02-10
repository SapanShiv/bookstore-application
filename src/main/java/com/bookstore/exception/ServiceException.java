package com.bookstore.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.bookstore.enums.ResponseErrorCodeEnum;

import lombok.Getter;
import lombok.ToString;

/**
 * Exception class for any exceptions due to technical reasons.
 * 
 * @author shivanimalhotra
 *
 */
@Getter
@ToString
public class ServiceException extends RuntimeException {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1671692640624832172L;

	/**
	 * LOGGER instance.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceException.class);

	/**
	 * Error code.
	 */
	private final String code;

	/**
	 * User message.
	 */
	private final String userMessage;

	/**
	 * HttpStatus.
	 */
	private final HttpStatus httpStatus;

	/**
	 * ResponseErrorCodeEnum instance.
	 */
	private final ResponseErrorCodeEnum errorCodeEnum;

	/**
	 * TechnicalException constructor.
	 * 
	 * @param message
	 *            - String
	 */
	public ServiceException(ResponseErrorCodeEnum errorCodeEnum) {
		super();
		this.errorCodeEnum = errorCodeEnum;
		this.code = String.valueOf(errorCodeEnum.getCode());
		this.userMessage = String.valueOf(errorCodeEnum.getDescription());
		this.httpStatus = errorCodeEnum.getHttpStatus();
	}

	/**
	 * Constructor with the specific error message.
	 * 
	 * @param errorCodeEnum
	 *            - ResponseErrorCodeEnum
	 * @param errorMessage
	 *            - String
	 */
	public ServiceException(ResponseErrorCodeEnum errorCodeEnum, String errorMessage) {
		super(errorMessage);
		this.errorCodeEnum = errorCodeEnum;
		this.userMessage = errorMessage;
		this.code = String.valueOf(errorCodeEnum.getCode());
		this.httpStatus = errorCodeEnum.getHttpStatus();
	}

	/**
	 * Constructor with specific message and throwable.
	 * 
	 * @param errorCodeEnum
	 *            - ResponseErrorCodeEnum
	 * @param errorMessage
	 *            - String
	 * @param t
	 *            - Throwable
	 */
	public ServiceException(ResponseErrorCodeEnum errorCodeEnum, String errorMessage, Throwable t) {
		super(errorMessage, t);
		this.errorCodeEnum = errorCodeEnum;
		this.userMessage = errorMessage;
		this.code = String.valueOf(errorCodeEnum.getCode());
		this.httpStatus = errorCodeEnum.getHttpStatus();
	}

	/**
	 * Constructor with Throwable.
	 * 
	 * @param errorCodeEnum
	 *            - ResponseErrorCodeEnum
	 * @param cause
	 *            - Throwable
	 */
	public ServiceException(ResponseErrorCodeEnum errorCodeEnum, Throwable cause) {
		super(cause);
		this.errorCodeEnum = errorCodeEnum;
		this.code = String.valueOf(errorCodeEnum.getCode());
		this.userMessage = String.valueOf(errorCodeEnum.getDescription());
		this.httpStatus = errorCodeEnum.getHttpStatus();

	}

	/**
	 * Constructor with Exception argument.
	 * 
	 * @param errorCodeEnum
	 *            - ResponseErrorCodeEnum
	 * @param innerException
	 *            - Exception
	 */
	public ServiceException(ResponseErrorCodeEnum errorCodeEnum, Exception innerException) {
		super(innerException);
		String exceptionMessage = innerException.getMessage();
		this.errorCodeEnum = errorCodeEnum;
		this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		this.userMessage = ResponseErrorCodeEnum.GENERIC_EXCEPTION.getDescription();
		this.code = ResponseErrorCodeEnum.GENERIC_EXCEPTION.getCode();
		LOGGER.error(exceptionMessage, innerException);
	}

}
