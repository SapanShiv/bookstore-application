package com.bookstore.model;

import java.io.Serializable;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.bookstore.constants.ErrorConstants;

import lombok.Data;

/**
 * TO containing the attributes for BookStore.
 * 
 * @author shivanimalhotra
 *
 */
@Data
public class BookStoreTO implements Serializable {

	/**
	 * SerialVersionUID.
	 */
	private static final long serialVersionUID = -2467918964732036081L;

	/**
	 * Book ISBN number.
	 */
	@NotNull
	@Length(min = 13, max = 13, message = ErrorConstants.ISBN_NUMBER_NOT_VALID)
	private String isbn;

	/**
	 * Book title.
	 */
	@NotNull
	@Size(min = 3, max = 255, message = ErrorConstants.TITLE_LENGTH_INVALID)
	private String title;

	/**
	 * Book author name.
	 */
	@NotBlank(message = ErrorConstants.AUTHOR_NAME_INVALID)
	private String authorName;

	/**
	 * Book price amount.
	 */
	@NotNull
	@Digits(integer = 8, fraction = 2, message = ErrorConstants.ARGUMENTS_NOT_VALID)
	private double price;

	/**
	 * Book copies count.
	 */
	private Integer copies;

}
