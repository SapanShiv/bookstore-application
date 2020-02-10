package com.bookstore.service;

import org.springframework.stereotype.Component;

/**
 * Interface for Book buy service.
 * 
 * @author shivanimalhotra
 *
 */
@Component
public interface BookBuyService {

	/**
	 * Method to buy a book with given isbn.
	 * 
	 * @param isbn
	 *            - String
	 * @return - String
	 */
	public String buyBook(String isbn);

}
