package com.bookstore.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookstore.constants.BookStoreConstants;
import com.bookstore.entity.BookEntity;

/**
 * Custom Repository implementation class for search.
 * 
 * @author shivanimalhotra
 *
 */
@Repository
public class CustomBookSearchRepository implements BookSearchRepository {

	/**
	 * EntityManager instance.
	 */
	@Autowired
	EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bookstore.repository.BookSearchRepository#searchBooks(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public List<BookEntity> searchBooks(String isbn, String title, String author) {
		final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		final CriteriaQuery<BookEntity> cq = cb.createQuery(BookEntity.class);
		final Root<BookEntity> root = cq.from(BookEntity.class);
		final Predicate isIsbn = cb.equal(root.get(BookStoreConstants.ISBN_NUMBER), isbn);
		final Predicate isTitle = cb.like(root.get(BookStoreConstants.TITLE), BookStoreConstants.PERCENT + title + BookStoreConstants.PERCENT);
		final Predicate isAuthor = cb.like(root.get(BookStoreConstants.AUTHOR).get(BookStoreConstants.AUTHOR_NAME_FIELD), BookStoreConstants.PERCENT + author + BookStoreConstants.PERCENT);
		final Predicate whereClause = cb.or(isIsbn, isTitle, isAuthor);
		cq.where(whereClause);
		return entityManager.createQuery(cq).getResultList();
	}

}
