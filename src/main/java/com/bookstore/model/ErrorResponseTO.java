package com.bookstore.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Error Response TO.
 * 
 * @author shivanimalhotra
 *
 */
@Data
@NoArgsConstructor
public class ErrorResponseTO {

	/**
	 * @param message
	 * @param details
	 */
	public ErrorResponseTO(String message, List<Error> details) {
		super();
		this.message = message;
		this.errorDetails = details;
	}

	/**
	 * The message.
	 */
	private String message;

	/**
	 * The error.
	 */
	private boolean error;

	/**
	 * The Error details.
	 */
	private List<Error> errorDetails;

}
