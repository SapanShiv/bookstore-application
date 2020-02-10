package com.bookstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookstore.entity.BookEntity;

/**
 * Book Repository.
 * 
 * @author shivanimalhotra
 *
 */
@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

	/**
	 * Method to find the bookEntity based on isbn.
	 * 
	 * @param isbn
	 *            - String
	 * @param title
	 *            - String
	 * @param author
	 *            - String
	 * @param price
	 *            - double
	 * @return - BookEntity
	 */
	@Query("SELECT be FROM BookEntity be JOIN FETCH be.author a " + "WHERE be.isbn =:isbn and be.title =:title and a.authorName =:author and be.price =:price")
	public Optional<BookEntity> findByIsbnAndTitleAndAuthorAndPrice(String isbn, String title, String author, double price);

	/**
	 * Method to search title of the book based on its isbn number.
	 * 
	 * @param isbn
	 *            - String
	 * @return - Optional<String>
	 */
	@Query("select be.title from BookEntity be where be.isbn =:isbn")
	public Optional<String> findTitleByIsbn(@Param("isbn") String isbn);

	/**
	 * Method to find bookEntity by its isbn number.
	 * 
	 * @param isbn
	 *            - String
	 * @return - Optional<BookEntity>
	 */
	public Optional<BookEntity> findByIsbn(String isbn);

}
