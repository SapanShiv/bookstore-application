package com.bookstore.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bookstore.entity.BookEntity;
import com.bookstore.model.BookStoreTO;

/**
 * Mapper interface for BookStore Mapper.
 * 
 * @author shivanimalhotra
 *
 */
@Mapper(componentModel = "spring")
public interface BookStoreMapper {

	/**
	 * Method to map BookStoreTO to BookEntity.
	 * 
	 * @param bookStoreTO
	 *            - BookStoreTO
	 * @return - BookEntity
	 */
	@Mapping(source = "authorName", target = "author.authorName")
	public BookEntity bookStoreTOToBookEntity(BookStoreTO bookStoreTO);

	/**
	 * @param bookEntity
	 *            - BookEntity
	 * @return - BookStoreTO
	 */
	@Mapping(source = "author.authorName", target = "authorName")
	public BookStoreTO bookEntityToBookStoreTO(BookEntity bookEntity);

	/**
	 * Method to map List of BookStoreEntity to List of BookStoreTO.
	 * 
	 * @param bookStoreTOList
	 *            - List<BookEntity>
	 * @return -List<BookStoreTO>
	 */
	public List<BookStoreTO> bookStoreEntityListToBookStoreTOList(List<BookEntity> bookEntityList);

}
