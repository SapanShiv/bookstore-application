package com.bookstore.enums;

import org.springframework.http.HttpStatus;

import com.bookstore.constants.ErrorConstants;

import lombok.Getter;
import lombok.ToString;

/**
 * Response Error code enum.
 * 
 * @author shivanimalhotra
 *
 */
@Getter
@ToString
public enum ResponseErrorCodeEnum {

	/**
	 * GENERIC_EXCEPTION.
	 */
	GENERIC_EXCEPTION("100", ErrorConstants.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR), SUCCESS("0", "Success.", HttpStatus.BAD_REQUEST),

	/**
	 * ARGUMENTS_NOT_VALID.
	 */
	ARGUMENTS_NOT_VALID("101", ErrorConstants.ARGUMENTS_NOT_VALID, HttpStatus.BAD_REQUEST),

	/**
	 * STALE_STATE_EXCEPTION.
	 */
	STALE_STATE_EXCEPTION("102", ErrorConstants.STALE_STATE_EXCEPTION, HttpStatus.CONFLICT),

	/**
	 * FETCHING_MEDIA_COVERAGE_ERROR.
	 */
	FETCHING_MEDIA_COVERAGE_ERROR("103", ErrorConstants.FETCHING_MEDIA_COVERAGE_ERROR, HttpStatus.INTERNAL_SERVER_ERROR),

	/**
	 * BOOK_NOT_FOUND.
	 */
	BOOK_NOT_FOUND("104,", ErrorConstants.BOOK_NOT_FOUND, HttpStatus.NO_CONTENT),

	/**
	 * ISBN_NUMBER_NOT_VALID.
	 */
	ISBN_NUMBER_NOT_VALID("105", ErrorConstants.ISBN_NUMBER_NOT_VALID, HttpStatus.BAD_REQUEST),

	/**
	 * TITLE_LENGTH_INVALID.
	 */
	TITLE_LENGTH_INVALID("106", ErrorConstants.TITLE_LENGTH_INVALID, HttpStatus.BAD_REQUEST),

	/**
	 * AUTHOR_NAME_INVALID.
	 */
	AUTHOR_NAME_INVALID("107", ErrorConstants.AUTHOR_NAME_INVALID, HttpStatus.BAD_REQUEST);

	/**
	 * Constructor.
	 * 
	 * @param code
	 * @param description
	 * @param httpStatus
	 */
	ResponseErrorCodeEnum(String code, String description, HttpStatus httpStatus) {
		this.code = code;
		this.description = description;
		this.httpStatus = httpStatus;
	}

	/**
	 * Error code.
	 */
	private String code;

	/**
	 * Error description.
	 */
	private String description;

	/**
	 * HttpStatus code.
	 */
	private HttpStatus httpStatus;

}
