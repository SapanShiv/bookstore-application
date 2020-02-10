package com.bookstore.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.bookstore.constants.ErrorConstants;
import com.bookstore.entity.BookEntity;
import com.bookstore.enums.ResponseErrorCodeEnum;
import com.bookstore.exception.BusinessException;
import com.bookstore.exception.ServiceException;
import com.bookstore.mapper.BookStoreMapper;
import com.bookstore.model.BookStoreTO;
import com.bookstore.model.MediaCoverageResponseTO;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.BookSearchRepository;
import com.bookstore.service.BookSearchService;
import com.bookstore.service.MediaCoverageService;
import com.bookstore.validator.BookStoreValidator;

/**
 * Service layer to implement the business scenarios fr searching the books of book store.
 * 
 * @author shivanimalhotra
 *
 */
@Service
public class BookSearchServiceImpl implements BookSearchService {

	/**
	 * Logger instance.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(BookSearchServiceImpl.class);

	/**
	 * BookStoreValidator instance.
	 */
	@Autowired
	private BookStoreValidator bookStoreValidator;

	/**
	 * BookSearchRepository instance.
	 */
	@Autowired
	private BookSearchRepository bookSearchRepository;

	/**
	 * BookStoreMapper instance.
	 */
	@Autowired
	private BookStoreMapper bookStoreMapper;

	/**
	 * BookRepository instance.
	 */
	@Autowired
	private BookRepository bookRepository;

	/**
	 * MediaCoverageService instance.
	 */
	@Autowired
	private MediaCoverageService mediaCoverageService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bookstore.service.BookSearchService#searchBooks(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public List<BookStoreTO> searchBooks(String isbn, String title, String author) {
		LOGGER.debug("Books are searched based on isbn: {}, title: {}, author: {}", isbn, title, author);
		if (bookStoreValidator.validateAllArgumentsNotNull(isbn, title, author)) {
			List<BookEntity> bookEntityList = bookSearchRepository.searchBooks(isbn, title, author);
			if (CollectionUtils.isEmpty(bookEntityList)) {
				throw new ServiceException(ResponseErrorCodeEnum.BOOK_NOT_FOUND);
			}
			return bookStoreMapper.bookStoreEntityListToBookStoreTOList(bookEntityList);
		} else {
			LOGGER.error(ErrorConstants.ARGUMENTS_NOT_VALID);
			throw new BusinessException(ResponseErrorCodeEnum.ARGUMENTS_NOT_VALID);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bookstore.service.BookSearchService#searchMediaCoverage(java.lang.String)
	 */
	@Override
	public List<String> searchMediaCoverage(String isbn) {
		LOGGER.info("Searching media coverage titles with isbn number: {}", isbn);
		final List<MediaCoverageResponseTO> mediaCoverageList = mediaCoverageService.getMediaCoverage();
		if (!bookStoreValidator.validateIsbn(isbn)) {
			throw new BusinessException(ResponseErrorCodeEnum.ISBN_NUMBER_NOT_VALID);
		}
		Optional<String> title = bookRepository.findTitleByIsbn(isbn);
		if (title.isPresent()) {
			String bookTitle = title.get();
			LOGGER.info("Book title with isbn: {}", bookTitle);
			return extractTitlesFromMediaCoverage(mediaCoverageList, bookTitle);
		} else {
			LOGGER.error("Book not found with given isbn: {}", isbn);
			throw new BusinessException(ResponseErrorCodeEnum.BOOK_NOT_FOUND);
		}
	}

	/**
	 * Method to extract media coverage titles from title and body based on the book title.
	 * 
	 * @param mediaCoverageList
	 *            - List<MediaCoverageResponseTO>
	 * @param bookTitle
	 *            - String
	 * @return - List<String>
	 */
	private List<String> extractTitlesFromMediaCoverage(final List<MediaCoverageResponseTO> mediaCoverageList, final String bookTitle) {
		final Predicate<MediaCoverageResponseTO> bookTitleInMediaTitle = media -> media.getTitle().contains(bookTitle);
		final Predicate<MediaCoverageResponseTO> bookTitleInMediaBody = media -> media.getBody().contains(bookTitle);
		return mediaCoverageList.stream().filter(bookTitleInMediaTitle.or(bookTitleInMediaBody)).map(MediaCoverageResponseTO::getTitle).collect(Collectors.toList());
	}

}
