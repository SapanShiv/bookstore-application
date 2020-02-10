package com.bookstore.service.impl;

import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.bookstore.entity.AuthorEntity;
import com.bookstore.entity.BookEntity;
import com.bookstore.exception.BusinessException;
import com.bookstore.model.BookStoreTO;
import com.bookstore.repository.BookRepository;
import com.bookstore.validator.BookStoreValidator;

import junit.framework.Assert;

/**
 * Unit test for BookBuyServiceImpl.
 * 
 * @author shivanimalhotra
 */
@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.class)
public class BookBuyServiceImplTest {

	/**
	 * ISBN constant.
	 */
	private static final String ISBN = "1234567890123";

	/**
	 * AUTHOR_NAME constant.
	 */
	private static final String AUTHOR_NAME = "Chetan Bhagat";

	/**
	 * TITLE constant.
	 */
	private static final String TITLE = "sit";

	/**
	 * PRICE constant.
	 */
	private static final String PRICE = "127.00";

	/**
	 * BookBuyServiceImpl instance.
	 */
	@InjectMocks
	private BookBuyServiceImpl bookBuyServiceImpl;

	/**
	 * BookStoreValidator instance.
	 */
	@Mock
	private BookStoreValidator bookStoreValidator;

	/**
	 * BookRepository instance.
	 */
	@Mock
	private BookRepository bookRepository;

	/**
	 * Test method for buyBook.
	 */
	@Test
	public void testBuyBook() {
		final BookStoreTO book = prepareBookStoreTO();
		final BookEntity bookEntity = prepareBookEntity();
		final List<BookEntity> bookEntityList = new ArrayList<>();
		final List<BookStoreTO> bookList = new ArrayList<>();
		bookEntityList.add(bookEntity);
		bookList.add(book);
		Mockito.when(bookStoreValidator.validateIsbn(ISBN)).thenReturn(true);
		Mockito.when(bookRepository.findByIsbn(book.getIsbn())).thenReturn(Optional.of(bookEntity));
		Mockito.when(bookRepository.save(bookEntity)).thenReturn(bookEntity);
		final String response = bookBuyServiceImpl.buyBook(ISBN);
		Mockito.verify(bookStoreValidator, times(1)).validateIsbn(ISBN);
		Mockito.verify(bookRepository, times(1)).findByIsbn(book.getIsbn());
		Mockito.verify(bookRepository, times(1)).save(bookEntity);
		Assert.assertEquals("Congrats! for the purchase.", response);

	}

	/**
	 * Test method for buyBook throwing Exception.
	 */
	@Test(expected = BusinessException.class)
	public void testBuyBookThrowingArgumentsNotValidException() {
		Mockito.when(bookStoreValidator.validateIsbn(ISBN)).thenReturn(false);

		bookBuyServiceImpl.buyBook(ISBN);

	}

	/**
	 * Test method for buyBook throwing Exception.
	 */
	@Test(expected = BusinessException.class)
	public void testBuyBookThrowingRecordNotFoundException() {
		final BookStoreTO book = prepareBookStoreTO();
		final BookEntity bookEntity = prepareBookEntity();
		final List<BookEntity> bookEntityList = new ArrayList<>();
		final List<BookStoreTO> bookList = new ArrayList<>();
		bookEntityList.add(bookEntity);
		bookList.add(book);
		Mockito.when(bookStoreValidator.validateIsbn(ISBN)).thenReturn(true);
		Mockito.when(bookRepository.findByIsbn(book.getIsbn())).thenReturn(Optional.empty());
		bookBuyServiceImpl.buyBook(ISBN);

	}

	/**
	 * Test method for buyBook.
	 */
	@Test
	public void testBuyBookOutOfStock() {
		final BookStoreTO book = prepareBookStoreTO();
		final BookEntity bookEntity = prepareBookEntity();
		bookEntity.setCopies(0);
		final List<BookEntity> bookEntityList = new ArrayList<>();
		final List<BookStoreTO> bookList = new ArrayList<>();
		bookEntityList.add(bookEntity);
		bookList.add(book);
		Mockito.when(bookStoreValidator.validateIsbn(ISBN)).thenReturn(true);
		Mockito.when(bookRepository.findByIsbn(book.getIsbn())).thenReturn(Optional.of(bookEntity));
		final String response = bookBuyServiceImpl.buyBook(ISBN);
		Mockito.verify(bookStoreValidator, times(1)).validateIsbn(ISBN);
		Mockito.verify(bookRepository, times(1)).findByIsbn(book.getIsbn());
		Assert.assertEquals("Book with isbn: " + ISBN + " is not available.", response);

	}

	/**
	 * Prepare BookEntity object.
	 * 
	 * @return - BookEntity
	 */
	private BookEntity prepareBookEntity() {
		final BookEntity entity = new BookEntity();
		final AuthorEntity authorEntity = new AuthorEntity();
		authorEntity.setAuthorName(AUTHOR_NAME);
		authorEntity.setId(1L);
		entity.setAuthor(authorEntity);
		entity.setCopies(3);
		entity.setId(1L);
		entity.setIsbn(ISBN);
		entity.setPrice(Double.parseDouble(PRICE));
		entity.setTitle(TITLE);
		return entity;
	}

	/**
	 * Method to prepare book object.
	 * 
	 * @return - BookStoreTO
	 */
	private BookStoreTO prepareBookStoreTO() {
		BookStoreTO book = new BookStoreTO();
		book.setAuthorName(AUTHOR_NAME);
		book.setCopies(5);
		book.setIsbn(ISBN);
		book.setPrice(Double.parseDouble(PRICE));
		book.setTitle(TITLE);
		return book;
	}

}
