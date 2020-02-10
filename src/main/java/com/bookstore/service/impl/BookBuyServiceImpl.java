package com.bookstore.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.StaleStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entity.BookEntity;
import com.bookstore.enums.ResponseErrorCodeEnum;
import com.bookstore.exception.BusinessException;
import com.bookstore.exception.ServiceException;
import com.bookstore.repository.BookRepository;
import com.bookstore.service.BookBuyService;
import com.bookstore.validator.BookStoreValidator;

/**
 * Service layer to implement the business scenarios fr searching the books of book store.
 * 
 * @author shivanimalhotra
 *
 */
@Service
public class BookBuyServiceImpl implements BookBuyService {

	/**
	 * Logger instance.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(BookBuyServiceImpl.class);

	/**
	 * BookRepository instance.
	 */
	@Autowired
	private BookRepository bookRepository;

	/**
	 * BookStoreValidator instance.
	 */
	@Autowired
	private BookStoreValidator bookStoreValidator;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bookstore.service.BookSearchService#searchMediaCoverage(java.lang.String)
	 */
	@Transactional
	@Override
	public String buyBook(String isbn) {
		if (!bookStoreValidator.validateIsbn(isbn)) {
			throw new BusinessException(ResponseErrorCodeEnum.ISBN_NUMBER_NOT_VALID);
		}
		LOGGER.info("Buying a book with isbn number: {}", isbn);
		try {
			Optional<BookEntity> bookEntity = bookRepository.findByIsbn(isbn);
			if (bookEntity.isPresent()) {
				BookEntity book = bookEntity.get();
				if (book.getCopies() > 0) {
					book.setCopies(book.getCopies() - 1);
					bookRepository.save(book);
				} else {
					return "Book with isbn: " + isbn + " is not available.";
				}
			} else {
				LOGGER.error("Book not found with given isbn: {}", isbn);
				throw new BusinessException(ResponseErrorCodeEnum.BOOK_NOT_FOUND);
			}
		} catch (StaleStateException ex) {
			LOGGER.error("Stale state found while buying a book with isbn {} ", isbn);
			throw new ServiceException(ResponseErrorCodeEnum.STALE_STATE_EXCEPTION, ex.getMessage());
		} 
		return "Congrats! for the purchase.";
	}

}
