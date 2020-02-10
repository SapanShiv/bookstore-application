package com.bookstore.controller;

import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bookstore.controller.rest.BookStoreRestController;
import com.bookstore.model.BookStoreTO;
import com.bookstore.service.BookBuyService;
import com.bookstore.service.BookSearchService;
import com.bookstore.service.BookStoreService;

import junit.framework.Assert;

/**
 * Unit test for BookStoreRestController.
 * 
 * @author shivanimalhotra
 */
@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.class)
public class BookStoreRestControllerTest {

	/**
	 * ISBN constant.
	 */
	private static final String ISBN = "1234567890123";

	/**
	 * BookStoreRestController instance.
	 */
	@InjectMocks
	private BookStoreRestController bookStoreRestController;

	/**
	 * BookStoreService instance.
	 */
	@Mock
	private BookStoreService bookStoreService;

	/**
	 * BookSearchService instance.
	 */
	@Mock
	private BookSearchService bookSearchService;

	/**
	 * BookBuyService instance.
	 */
	@Mock
	private BookBuyService bookBuyService;

	/**
	 * Test method for addBook.
	 */
	@Test
	public void testAddBook() {
		final BookStoreTO book = prepareBookStoreTO();
		Mockito.when(bookStoreService.saveBook(book)).thenReturn(book);
		ResponseEntity<BookStoreTO> response = bookStoreRestController.addBook(book);
		ArgumentCaptor<BookStoreTO> bookStoreCaptor = ArgumentCaptor.forClass(BookStoreTO.class);
		Mockito.verify(bookStoreService, times(1)).saveBook(bookStoreCaptor.capture());
		final BookStoreTO bookStoreTO = bookStoreCaptor.getValue();
		Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		Assert.assertEquals("Chetan Bhagat", bookStoreTO.getAuthorName());

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
		book.setIsbn("1234567890123");
		book.setPrice(127.00);
		book.setTitle("3 mistakes of my life");
		return book;
	}

	/**
	 * Method to test searchBooks method.
	 */
	@Test
	public void testSearchBooks() {
		List<BookStoreTO> booksList = new ArrayList<>();
		BookStoreTO book = prepareBookStoreTO();
		booksList.add(book);
		Mockito.when(bookSearchService.searchBooks(book.getIsbn(), book.getTitle(), book.getAuthorName())).thenReturn(booksList);
		ResponseEntity<List<BookStoreTO>> response = bookStoreRestController.searchBooks(book.getIsbn(), book.getTitle(), book.getAuthorName());
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	/**
	 * Method to test searchBookMediaCoverage method.
	 */
	@Test
	public void testSearchBookMediaCoverage() {
		List<String> titlesList = new ArrayList<>();
		String title = "adipisci placeat illum aut reiciendis qui";
		titlesList.add(title);
		Mockito.when(bookSearchService.searchMediaCoverage(ISBN)).thenReturn(titlesList);
		ResponseEntity<List<String>> response = bookStoreRestController.searchBookMediaCoverage(ISBN);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertEquals(title, response.getBody().get(0));
	}

	/**
	 * Method to test buyBook method.
	 */
	@Test
	public void testBuyBook() {
		String buyResponse = "Success";
		Mockito.when(bookBuyService.buyBook(ISBN)).thenReturn(buyResponse);
		ResponseEntity<String> response = bookStoreRestController.buyBook(ISBN);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertEquals(buyResponse, response.getBody());
	}
}
