package com.bookstore.controller.rest;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.controller.api.BookStoreController;
import com.bookstore.model.BookStoreTO;
import com.bookstore.service.BookBuyService;
import com.bookstore.service.BookSearchService;
import com.bookstore.service.BookStoreService;

/**
 * Rest controller for all the api's comprising book store application.
 * 
 * @author shivanimalhotra
 *
 */
@RestController
public class BookStoreRestController implements BookStoreController {

	/**
	 * Logger instance.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(BookStoreRestController.class);

	/**
	 * BookStoreService instance.
	 */
	@Autowired
	private BookStoreService bookStoreService;

	/**
	 * BookSearchService instance.
	 */
	@Autowired
	private BookSearchService bookSearchService;

	/**
	 * BookBuyService instance.
	 */
	@Autowired
	private BookBuyService bookBuyService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bookstore.controller.api.BookStoreController#addBook(com.bookstore.controller.dto.
	 * BookStoreTO)
	 */
	public ResponseEntity<BookStoreTO> addBook(@RequestBody @Valid BookStoreTO book) {
		LOGGER.info("Add book api called with request {}", book);
		BookStoreTO response = bookStoreService.saveBook(book);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bookstore.controller.api.BookStoreController#searchBooks(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public ResponseEntity<List<BookStoreTO>> searchBooks(String isbn, String title, String author) {
		LOGGER.info("Search book based on isbn: {}, title: {}, author: {}", isbn, title, author);
		List<BookStoreTO> bookEntityList = bookSearchService.searchBooks(isbn, title, author);
		return new ResponseEntity<>(bookEntityList, HttpStatus.OK);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bookstore.controller.api.BookStoreController#searchBookMediaCoverage(java.lang.String)
	 */
	@Override
	public ResponseEntity<List<String>> searchBookMediaCoverage(String isbn) {
		LOGGER.info("Search media coverage of the book with isbn: {}", isbn);
		List<String> mediaCoverageTitles = bookSearchService.searchMediaCoverage(isbn);
		return new ResponseEntity<>(mediaCoverageTitles, HttpStatus.OK);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bookstore.controller.api.BookStoreController#buyBook(java.lang.String)
	 */
	@Override
	public ResponseEntity<String> buyBook(String isbn) {
		LOGGER.info("Buying book with isbn: {}", isbn);
		String result = bookBuyService.buyBook(isbn);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
