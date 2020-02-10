package com.bookstore.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * This class contains the error code and error message.
 *
 * @author shivanimalhotra
 */
@Data
@AllArgsConstructor
public class Error implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7628059486570669487L;

	/**
	 * The errorCode.
	 */
	private String errorCode;

	/**
	 * The errorMessage.
	 */
	private String errorMessage;

}
