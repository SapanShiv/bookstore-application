package com.bookstore.validator.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import com.bookstore.constants.BookStoreConstants;
import com.bookstore.model.BookStoreTO;
import com.bookstore.validator.BookStoreValidator;

/**
 * Validator implementation class for validating different object and their parameters.
 * 
 * @author shivanimalhotra
 *
 */
@Service
public class BookStoreValidatorImpl implements BookStoreValidator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bookstore.validator.BookStoreValidator#validateBookStoreData(com.bookstore.controller.dto
	 * .BookStoreTO)
	 */
	@Override
	public boolean validateBookStoreData(BookStoreTO bookStoreTO) {
		return !ObjectUtils.isEmpty(bookStoreTO) || !StringUtils.isEmpty(bookStoreTO.getAuthorName()) || !StringUtils.isEmpty(bookStoreTO.getTitle()) || !StringUtils.isEmpty(bookStoreTO.getIsbn())
				|| !Double.isNaN(bookStoreTO.getPrice());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bookstore.validator.BookStoreValidator#validateAllArgumentsNotNull(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public boolean validateAllArgumentsNotNull(String isbn, String title, String author) {
		return StringUtils.isNotEmpty(isbn) && StringUtils.isNotEmpty(title) && StringUtils.isNotEmpty(author);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bookstore.validator.BookStoreValidator#validateIsbn(java.lang.String)
	 */
	@Override
	public boolean validateIsbn(String isbn) {
		return !(StringUtils.isEmpty(isbn) || isbn.length() != Integer.parseInt(BookStoreConstants.THIRTEEN_CONSTANT));
	}

}
