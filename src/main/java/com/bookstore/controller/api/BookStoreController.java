package com.bookstore.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookstore.constants.BookStoreConstants;
import com.bookstore.model.BookStoreTO;

/**
 * Controller for BookStore Application.
 * 
 * @author shivanimalhotra
 *
 */
@RequestMapping(value = "/api/book")
public interface BookStoreController {

	/**
	 * @param book
	 *            - BookStoreTO
	 * @return - ResponseEntity<BookStoreTO>
	 */
	@PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookStoreTO> addBook(@RequestBody @Valid BookStoreTO book);

	/**
	 * Method to search books based on isbn or title or author.
	 * 
	 * @param isbn
	 *            - String
	 * @param title
	 *            - String
	 * @param author
	 *            - String
	 * @return - ResponseEntity<List<BookStoreTO>>
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BookStoreTO>> searchBooks(@RequestParam(value = BookStoreConstants.ISBN_NUMBER, required = false) String isbn,
			@RequestParam(value = BookStoreConstants.TITLE, required = false) String title, @RequestParam(value = BookStoreConstants.AUTHOR, required = false) String author);

	/**
	 * Method to search titles from the media coverage of the books based on the given isbn number.
	 * 
	 * @param isbn
	 *            - String
	 * @return - ResponseEntity<List<String>>
	 */
	@GetMapping(value = "/media", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> searchBookMediaCoverage(@RequestParam(value = BookStoreConstants.ISBN_NUMBER) String isbn);

	/**
	 * Method to buy a book based on ts isbn number.
	 * 
	 * @param isbn
	 *            - String
	 * @return - ResponseEntity<String>
	 */
	@GetMapping(value = "/buy", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> buyBook(@RequestParam(value = BookStoreConstants.ISBN_NUMBER) String isbn);

}
