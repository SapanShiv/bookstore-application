package com.bookstore.service.impl;

import static org.mockito.Mockito.times;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.bookstore.entity.AuthorEntity;
import com.bookstore.entity.BookEntity;
import com.bookstore.enums.ResponseErrorCodeEnum;
import com.bookstore.exception.ServiceException;
import com.bookstore.mapper.BookStoreMapper;
import com.bookstore.model.BookStoreTO;
import com.bookstore.repository.BookRepository;
import com.bookstore.validator.BookStoreValidator;

import junit.framework.Assert;

/**
 * Unit test for BookStoreServiceImpl.
 * 
 * @author shivanimalhotra
 */
@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.class)
public class BookStoreServiceImplTest {

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
	private static final String TITLE = "3 mistakes of my life";

	/**
	 * PRICE constant.
	 */
	private static final String PRICE = "127.00";

	/**
	 * BookStoreServiceImpl instance.
	 */
	@InjectMocks
	private BookStoreServiceImpl bookStoreServiceImpl;

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
	 * BookStoreMapper instance.
	 */
	@Mock
	private BookStoreMapper bookStoreMapper;

	/**
	 * Test method for saveBook.
	 */
	@Test
	public void testSaveBook() {
		final BookStoreTO book = prepareBookStoreTO();
		final BookEntity bookEntity = prepareBookEntity();
		Mockito.when(bookStoreValidator.validateBookStoreData(book)).thenReturn(true);
		Mockito.when(bookRepository.findByIsbnAndTitleAndAuthorAndPrice(book.getIsbn(), book.getTitle(), book.getAuthorName(), book.getPrice())).thenReturn(Optional.of(bookEntity));
		Mockito.when(bookRepository.save(bookEntity)).thenReturn(bookEntity);
		Mockito.when(bookStoreMapper.bookEntityToBookStoreTO(bookEntity)).thenReturn(book);
		final BookStoreTO response = bookStoreServiceImpl.saveBook(book);
		ArgumentCaptor<BookEntity> bookEntityCaptor = ArgumentCaptor.forClass(BookEntity.class);
		Mockito.verify(bookStoreValidator, times(1)).validateBookStoreData(response);
		Mockito.verify(bookRepository, times(1)).findByIsbnAndTitleAndAuthorAndPrice(book.getIsbn(), book.getTitle(), book.getAuthorName(), book.getPrice());
		Mockito.verify(bookRepository, times(1)).save(bookEntityCaptor.capture());
		Mockito.verify(bookStoreMapper, times(1)).bookEntityToBookStoreTO(bookEntityCaptor.capture());
		Assert.assertEquals(AUTHOR_NAME, response.getAuthorName());

	}

	/**
	 * Test method for saveBook throwing Technical Exception.
	 */
	@Test(expected = ServiceException.class)
	public void testSaveBookThrowingTechnicalException() {
		final BookStoreTO book = prepareBookStoreTO();
		Mockito.when(bookStoreValidator.validateBookStoreData(book)).thenThrow(new ServiceException(ResponseErrorCodeEnum.GENERIC_EXCEPTION, "Error occurred"));

		bookStoreServiceImpl.saveBook(book);

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
