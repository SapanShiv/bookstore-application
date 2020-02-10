package com.bookstore.repository;

import java.util.List;

import com.bookstore.entity.BookEntity;

/**
 * Repository layer for searching the books.
 * 
 * @author shivanimalhotra
 *
 */
public interface BookSearchRepository {

	/**
	 * Method to search the books based on either isbn or title or author.
	 * 
	 * @param isbn
	 *            - String
	 * @param title
	 *            - String
	 * @param author
	 *            - String
	 * @return - List<BookEntity>
	 */
	public List<BookEntity> searchBooks(String isbn, String title, String author);

}
