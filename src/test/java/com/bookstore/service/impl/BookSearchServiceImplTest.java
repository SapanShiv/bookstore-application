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
import com.bookstore.exception.ServiceException;
import com.bookstore.mapper.BookStoreMapper;
import com.bookstore.model.BookStoreTO;
import com.bookstore.model.MediaCoverageResponseTO;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.BookSearchRepository;
import com.bookstore.service.MediaCoverageService;
import com.bookstore.validator.BookStoreValidator;

import junit.framework.Assert;

/**
 * Unit test for BookSearchServiceImpl.
 * 
 * @author shivanimalhotra
 */
@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.class)
public class BookSearchServiceImplTest {

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
	 * BookSearchServiceImpl instance.
	 */
	@InjectMocks
	private BookSearchServiceImpl bookSearchServiceImpl;

	/**
	 * BookStoreValidator instance.
	 */
	@Mock
	private BookStoreValidator bookStoreValidator;

	/**
	 * BookSearchRepository instance.
	 */
	@Mock
	private BookSearchRepository bookSearchRepository;

	/**
	 * BookStoreMapper instance.
	 */
	@Mock
	private BookStoreMapper bookStoreMapper;

	/**
	 * BookRepository instance.
	 */
	@Mock
	private BookRepository bookRepository;

	/**
	 * MediaCoverageService instance.
	 */
	@Mock
	private MediaCoverageService mediaCoverageService;

	/**
	 * Test method for searchBooks.
	 */
	@Test
	public void testSearchBooks() {
		final BookStoreTO book = prepareBookStoreTO();
		final BookEntity bookEntity = prepareBookEntity();
		final List<BookEntity> bookEntityList = new ArrayList<>();
		final List<BookStoreTO> bookList = new ArrayList<>();
		bookEntityList.add(bookEntity);
		bookList.add(book);
		Mockito.when(bookStoreValidator.validateAllArgumentsNotNull(ISBN, TITLE, AUTHOR_NAME)).thenReturn(true);
		Mockito.when(bookSearchRepository.searchBooks(book.getIsbn(), book.getTitle(), book.getAuthorName())).thenReturn(bookEntityList);
		Mockito.when(bookStoreMapper.bookStoreEntityListToBookStoreTOList(bookEntityList)).thenReturn(bookList);
		final List<BookStoreTO> response = bookSearchServiceImpl.searchBooks(ISBN, TITLE, AUTHOR_NAME);
		Mockito.verify(bookStoreValidator, times(1)).validateAllArgumentsNotNull(ISBN, TITLE, AUTHOR_NAME);
		Mockito.verify(bookSearchRepository, times(1)).searchBooks(book.getIsbn(), book.getTitle(), book.getAuthorName());
		Mockito.verify(bookStoreMapper, times(1)).bookStoreEntityListToBookStoreTOList(bookEntityList);
		Assert.assertEquals(AUTHOR_NAME, response.get(0).getAuthorName());

	}

	/**
	 * Test method for searchBooks throwing Exception.
	 */
	@Test(expected = BusinessException.class)
	public void testSearchBooksThrowingArgumentsNotValidException() {
		Mockito.when(bookStoreValidator.validateAllArgumentsNotNull(ISBN, TITLE, AUTHOR_NAME)).thenReturn(false);

		bookSearchServiceImpl.searchBooks(ISBN, TITLE, AUTHOR_NAME);

	}

	/**
	 * Test method for searchMediaCoverage.
	 */
	@Test
	public void testSearchMediaCoverage() {
		final MediaCoverageResponseTO mediaCoverageResponseTO = prepareMediaCoverageResponseTO();
		List<MediaCoverageResponseTO> mediaCoverageResponseTOList = new ArrayList<>();
		mediaCoverageResponseTOList.add(mediaCoverageResponseTO);
		Mockito.when(mediaCoverageService.getMediaCoverage()).thenReturn(mediaCoverageResponseTOList);
		Mockito.when(bookStoreValidator.validateIsbn(ISBN)).thenReturn(true);
		Mockito.when(bookRepository.findTitleByIsbn(ISBN)).thenReturn(Optional.of(TITLE));
		final List<String> response = bookSearchServiceImpl.searchMediaCoverage(ISBN);
		Mockito.verify(mediaCoverageService, times(1)).getMediaCoverage();
		Mockito.verify(bookStoreValidator, times(1)).validateIsbn(ISBN);
		Mockito.verify(bookRepository, times(1)).findTitleByIsbn(ISBN);
		Assert.assertEquals(mediaCoverageResponseTO.getTitle(), response.get(0));

	}

	/**
	 * Test method for searchMediaCoverage throwing Exception.
	 */
	@Test(expected = ServiceException.class)
	public void testsearchMediaCoverageThrowingRecordNotFoundException() {
		final MediaCoverageResponseTO mediaCoverageResponseTO = prepareMediaCoverageResponseTO();
		final List<MediaCoverageResponseTO> mediaCoverageResponseTOList = new ArrayList<>();
		mediaCoverageResponseTOList.add(mediaCoverageResponseTO);
		Mockito.when(mediaCoverageService.getMediaCoverage()).thenReturn(mediaCoverageResponseTOList);
		Mockito.when(bookStoreValidator.validateIsbn(ISBN)).thenReturn(true);
		Mockito.when(bookRepository.findTitleByIsbn(ISBN)).thenReturn(Optional.empty());
		bookSearchServiceImpl.searchMediaCoverage(ISBN);

	}

	/**
	 * Test method for searchMediaCoverage throwing Exception.
	 */
	@Test(expected = BusinessException.class)
	public void testsearchMediaCoverageThrowingArgumentsNotValidException() {
		final MediaCoverageResponseTO mediaCoverageResponseTO = prepareMediaCoverageResponseTO();
		final List<MediaCoverageResponseTO> mediaCoverageResponseTOList = new ArrayList<>();
		mediaCoverageResponseTOList.add(mediaCoverageResponseTO);
		Mockito.when(mediaCoverageService.getMediaCoverage()).thenReturn(mediaCoverageResponseTOList);
		Mockito.when(bookStoreValidator.validateIsbn(ISBN)).thenReturn(false);
		bookSearchServiceImpl.searchMediaCoverage(ISBN);

	}

	/**
	 * Method to prepare mediaCoverageResponseTO object.
	 * 
	 * @return - mediaCoverageResponseTO
	 */
	private MediaCoverageResponseTO prepareMediaCoverageResponseTO() {
		final MediaCoverageResponseTO mediaCoverageResponseTO = new MediaCoverageResponseTO();
		mediaCoverageResponseTO.setBody("illum quis cupiditate provident sit magnam\nea sed aut omnis\nveniam maiores ullam consequatur atque\nadipisci quo iste expedita sit quos voluptas");
		mediaCoverageResponseTO.setId(19L);
		mediaCoverageResponseTO.setTitle("adipisci placeat illum aut reiciendis qui");
		mediaCoverageResponseTO.setUserId(2L);
		return mediaCoverageResponseTO;
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
