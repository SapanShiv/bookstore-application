package com.bookstore.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookstore.model.BookStoreTO;

/**
 * Interface for Book search service.
 * 
 * @author shivanimalhotra
 *
 */
@Component
public interface BookSearchService {

	/**
	 * Method to search books based on isbn, title and author.
	 * 
	 * @param isbn
	 *            - String
	 * @param title
	 *            - String
	 * @param author
	 *            - String
	 * @return - List<BookStoreTO>
	 */
	public List<BookStoreTO> searchBooks(@RequestParam String isbn, @RequestParam String title, @RequestParam String author);

	/**
	 * Method to search media coverage about a book with given isbn.
	 * 
	 * @param isbn
	 *            - String
	 * @return - List<String>
	 */
	public List<String> searchMediaCoverage(@RequestParam String isbn);

}
