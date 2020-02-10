package com.bookstore.model;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 * Data Transfer object for Media Coverage API.
 * 
 * @author shivanimalhotra
 *
 */
@Data
public class MediaCoverageResponseTO implements Serializable {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 8998454274902572726L;

	/**
	 * UserId field.
	 */
	private Long userId;

	/**
	 * Id field.
	 */
	private Long id;

	/**
	 * Title field.
	 */
	private String title;

	/**
	 * Body field.
	 */
	private String body;

}
