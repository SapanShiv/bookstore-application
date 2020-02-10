package com.bookstore.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.bookstore.model.BookStoreTO;

/**
 * Interface for BookStore Service.
 * 
 * @author shivanimalhotra
 *
 */
@Component
public interface BookStoreService {

	/**
	 * Method to save book to book table in db.
	 * 
	 * @param bookStore
	 *            - BookStoreTO
	 * @return - BookStoreTO
	 */
	public BookStoreTO saveBook(@RequestBody BookStoreTO bookStore);

}
