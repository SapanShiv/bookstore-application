package com.bookstore.validator;

import org.springframework.stereotype.Component;

import com.bookstore.model.BookStoreTO;

/**
 * Interface to validate BookStore data.
 * 
 * @author shivanimalhotra
 *
 */
@Component
public interface BookStoreValidator {

	/**
	 * Mathod to validate mandatory information of BookStoreTO.
	 * 
	 * @param bookStoreTO
	 *            - BookStoreTO
	 * @return - boolean
	 */
	public boolean validateBookStoreData(BookStoreTO bookStoreTO);

	/**
	 * Method to validate if all the arguments passed to the method are not empty or null.
	 * 
	 * @param isbn
	 *            - String
	 * @param title
	 *            - String
	 * @param author
	 *            - String
	 * @return -boolean
	 */
	public boolean validateAllArgumentsNotNull(String isbn, String title, String author);

	/**
	 * Method to validate isbn.
	 * 
	 * @param isbn
	 *            - String
	 * @return - boolean
	 */
	public boolean validateIsbn(String isbn);

}
