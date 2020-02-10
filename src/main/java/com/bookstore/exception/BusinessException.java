package com.bookstore.exception;

import org.springframework.validation.BindingResult;

import com.bookstore.enums.ResponseErrorCodeEnum;

import lombok.Getter;
import lombok.ToString;

/**
 * BusinessException class.
 * 
 * @author shivanimalhotra
 *
 */
@Getter
@ToString
public class BusinessException extends RuntimeException {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -7865774402762146270L;

	/**
	 * @param errorCodeEnum
	 */
	public BusinessException(ResponseErrorCodeEnum errorCodeEnum) {
		super();
		this.errorCodeEnum = errorCodeEnum;
		this.code = String.valueOf(errorCodeEnum.getCode());
		this.userMessage = errorCodeEnum.getDescription();
	}

	/**
	 * Constructor with BindingResult.
	 * 
	 * @param errorCodeEnum
	 * @param bindingResult
	 */
	public BusinessException(ResponseErrorCodeEnum errorCodeEnum, BindingResult bindingResult) {
		super();
		this.bindingResult = bindingResult;
		this.errorCodeEnum = errorCodeEnum;
		this.code = String.valueOf(errorCodeEnum.getCode());
		this.userMessage = errorCodeEnum.getDescription();
	}

	/**
	 * Constructor with errorMessage.
	 * 
	 * @param errorCodeEnum
	 * @param errorMessage
	 */
	public BusinessException(ResponseErrorCodeEnum errorCodeEnum, String errorMessage) {
		super(errorMessage);
		this.errorCodeEnum = errorCodeEnum;
		this.userMessage = errorMessage;
		this.code = String.valueOf(errorCodeEnum.getCode());
	}

	/**
	 * Constructor with errorMessage and Throwable.
	 * 
	 * @param errorCodeEnum
	 * @param errorMessage
	 * @param t
	 */
	public BusinessException(ResponseErrorCodeEnum errorCodeEnum, String errorMessage, Throwable t) {
		super(errorMessage, t);
		this.errorCodeEnum = errorCodeEnum;
		this.userMessage = errorMessage;
		this.code = String.valueOf(errorCodeEnum.getCode());
	}

	/**
	 * Constructor with Throwable.
	 * 
	 * @param errorCodeEnum
	 * @param cause
	 */
	public BusinessException(ResponseErrorCodeEnum errorCodeEnum, Throwable cause) {
		super(cause);
		this.errorCodeEnum = errorCodeEnum;
		this.code = String.valueOf(errorCodeEnum.getCode());
		this.userMessage = cause.getMessage();
	}

	/**
	 * Error code.
	 */
	private final String code;

	/**
	 * User message.
	 */
	private final String userMessage;

	/**
	 * BindingResult instance.
	 */
	private BindingResult bindingResult;

	/**
	 * ResponseErrorCodeEnum instance.
	 */
	private final ResponseErrorCodeEnum errorCodeEnum;

}
