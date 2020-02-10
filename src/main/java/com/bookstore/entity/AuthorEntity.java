package com.bookstore.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.bookstore.constants.BookStoreConstants;

import lombok.Data;

/**
 * Entity for AUTHORS table in BOOK_STORE schema.
 * 
 * @author shivanimalhotra
 *
 */
@Entity
@Table(name = BookStoreConstants.AUTHORS_TABLE, uniqueConstraints = @UniqueConstraint(columnNames = { BookStoreConstants.AUTHOR_NAME }, name = BookStoreConstants.AUTHORS_UNIQUE_KEY_NAME))
@Data
public class AuthorEntity implements Serializable {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -6257770059840154621L;

	/**
	 * Id field.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = BookStoreConstants.AUTHOR_ID)
	private Long id;

	/**
	 * Author name field.
	 */
	@Column(name = BookStoreConstants.AUTHOR_NAME)
	private String authorName;

	/**
	 * Instance of column created On.
	 */
	@Column(name = BookStoreConstants.CREATED_ON_COLUMN, nullable = false)
	private LocalDateTime createdOn;

	/**
	 * Instance of column updated On.
	 */
	@Column(name = BookStoreConstants.UPDATED_ON, nullable = false)
	private LocalDateTime updatedOn;

	/**
	 * JoinTable annotation to join authors and books tables based on author_id.
	 */
	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<BookEntity> booksList;

	/**
	 * 
	 * this method is used to set createOn and updatedOn instance variable with current local date.
	 *
	 */
	@PrePersist
	public void prePersist() {
		createdOn = LocalDateTime.now();
		updatedOn = LocalDateTime.now();
	}

	/**
	 * 
	 * this method is used to set updatedOn instance variable with current local date.
	 *
	 */
	@PreUpdate
	public void voidPreInsert() {
		updatedOn = LocalDateTime.now();
	}
}
