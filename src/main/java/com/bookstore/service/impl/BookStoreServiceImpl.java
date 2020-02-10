package com.bookstore.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entity.BookEntity;
import com.bookstore.enums.ResponseErrorCodeEnum;
import com.bookstore.exception.ServiceException;
import com.bookstore.mapper.BookStoreMapper;
import com.bookstore.model.BookStoreTO;
import com.bookstore.repository.BookRepository;
import com.bookstore.service.BookStoreService;
import com.bookstore.validator.BookStoreValidator;

/**
 * Service layer to implement the business scenarios and interaction with DB.
 * 
 * @author shivanimalhotra
 *
 */
@Service
public class BookStoreServiceImpl implements BookStoreService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookStoreServiceImpl.class);

	/**
	 * BookStoreValidator instance.
	 */
	@Autowired
	private BookStoreValidator bookStoreValidator;

	/**
	 * BookStoreRepo instance.
	 */
	@Autowired
	private BookRepository bookRepository;

	/**
	 * BookStoreMapper instance.
	 */
	@Autowired
	private BookStoreMapper bookStoreMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bookstore.service.BookStoreService#saveBook(com.bookstore.controller.dto.BookStoreTO)
	 */
	@Override
	@Transactional
	public BookStoreTO saveBook(@Valid BookStoreTO bookStore) {
		LOGGER.info("Book store request to be saved is: {}", bookStore);
		BookEntity entity = null;
		try {
			if (bookStoreValidator.validateBookStoreData(bookStore)) {

				Optional<BookEntity> bookEntityOptional =
						bookRepository.findByIsbnAndTitleAndAuthorAndPrice(bookStore.getIsbn(), bookStore.getTitle(), bookStore.getAuthorName(), bookStore.getPrice());
				if (bookEntityOptional.isPresent()) {
					BookEntity bookEntity = bookEntityOptional.get();
					LOGGER.info("Book is already present in db with details: {}", bookEntity);
					bookEntity.setCopies(bookEntity.getCopies() + bookStore.getCopies());
					entity = bookRepository.save(bookEntity);
				} else {
					entity = bookRepository.save(bookStoreMapper.bookStoreTOToBookEntity(bookStore));
				}

			}
		} catch (ServiceException exception) {
			throw new ServiceException(ResponseErrorCodeEnum.GENERIC_EXCEPTION, exception.getMessage());
		} catch (Exception ex) {
			throw new ServiceException(ResponseErrorCodeEnum.GENERIC_EXCEPTION, ex.getMessage());
		}
		return bookStoreMapper.bookEntityToBookStoreTO(entity);
	}

}
