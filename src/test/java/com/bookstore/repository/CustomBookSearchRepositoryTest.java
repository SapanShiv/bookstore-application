package com.bookstore.repository;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.bookstore.constants.BookStoreConstants;
import com.bookstore.entity.BookEntity;

/**
 * Unit test for CustomBookSearchRepository.
 * 
 * @author shivanimalhotra
 */
@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.class)
public class CustomBookSearchRepositoryTest {

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
	 * CustomBookSearchRepository instance.
	 */
	@InjectMocks
	private CustomBookSearchRepository customBookSearchRepository;

	/**
	 * Mock EntityManager.
	 */
	@Mock
	EntityManager entityManager;

	/**
	 * Mock criteriaBuilder.
	 */
	@Mock
	CriteriaBuilder criteriaBuilder;

	/**
	 * Mock criteriaQuery.
	 */
	@Mock
	CriteriaQuery<Object> criteriaQuery;

	/**
	 * Mock type query.
	 */
	@Mock
	TypedQuery<Object> typedQuery;

	/**
	 * Mock root.
	 */
	@Mock
	Root<BookEntity> root;

	/**
	 * Mock path.
	 */
	@Mock
	Path<Object> path;

	/**
	 * Test method for searchBooks.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testSearchBooks() {
		@SuppressWarnings("rawtypes")
		Path pathMock = mock(Path.class);
		Mockito.when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
		Mockito.when(criteriaBuilder.createQuery(ArgumentMatchers.any())).thenReturn(criteriaQuery);
		Mockito.when(criteriaQuery.from(BookEntity.class)).thenReturn(root);
		Mockito.when(root.get(BookStoreConstants.ISBN_NUMBER)).thenReturn(pathMock);
		Mockito.when(root.get(BookStoreConstants.TITLE)).thenReturn(pathMock);
		Mockito.when(root.get(BookStoreConstants.AUTHOR)).thenReturn(pathMock);
		Mockito.when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
		Mockito.when(typedQuery.getResultList()).thenReturn(new ArrayList<>());
		assertNotNull(customBookSearchRepository.searchBooks(ISBN, TITLE, AUTHOR_NAME));

	}

}
