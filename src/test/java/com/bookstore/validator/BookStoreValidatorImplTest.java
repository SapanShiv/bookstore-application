package com.bookstore.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.bookstore.model.BookStoreTO;
import com.bookstore.validator.impl.BookStoreValidatorImpl;

import junit.framework.Assert;

/**
 * Unit test for BookStoreValidatorImpl.
 * 
 * @author shivanimalhotra
 */
@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.class)
public class BookStoreValidatorImplTest {

	/**
	 * ISBN constant.
	 */
	private static final String ISBN = "1234567890123";

	/**
	 * BookStoreValidatorImpl instance.
	 */
	@InjectMocks
	private BookStoreValidatorImpl bookStoreValidatorImpl;

	/**
	 * Test method for validateBookStoreData.
	 */
	@Test
	public void testValidateBookStoreData() {
		final BookStoreTO book = prepareBookStoreTO();
		boolean response = bookStoreValidatorImpl.validateBookStoreData(book);
		Assert.assertEquals(true, response);
	}

	/**
	 * Method to prepare book object.
	 * 
	 * @return - BookStoreTO
	 */
	private BookStoreTO prepareBookStoreTO() {
		BookStoreTO book = new BookStoreTO();
		book.setAuthorName("Chetan Bhagat");
		book.setCopies(5);
		book.setIsbn(ISBN);
		book.setPrice(127.00);
		book.setTitle("3 mistakes of my life");
		return book;
	}

	/**
	 * Test method for validateAllArgumentsNotNull.
	 */
	@Test
	public void testValidateAllArgumentsNotNull() {
		boolean response = bookStoreValidatorImpl.validateAllArgumentsNotNull(null, null, null);
		Assert.assertEquals(false, response);

	}

	/**
	 * Method to test validateIsbn method.
	 */
	@Test
	public void testValidateIsbn() {
		boolean response = bookStoreValidatorImpl.validateIsbn(ISBN);
		Assert.assertEquals(true, response);
	}

	/**
	 * Method to test validateIsbn method with invalid data.
	 */
	@Test
	public void testValidateIsbnInvalid() {
		boolean response = bookStoreValidatorImpl.validateIsbn("123");
		Assert.assertEquals(false, response);
	}
}
