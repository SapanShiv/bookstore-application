package com.bookstore.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import com.bookstore.constants.BookStoreConstants;

import lombok.Data;
import lombok.ToString;

/**
 * Entity for BOOKS table in BOOK_STORE schema.
 * 
 * @author shivanimalhotra
 *
 */
@Entity
@Table(name = BookStoreConstants.BOOKS_TABLE, uniqueConstraints = @UniqueConstraint(columnNames = { BookStoreConstants.ISBN_NUMBER }, name = BookStoreConstants.BOOKS_UNIQUE_KEY_NAME))
@Data
public class BookEntity implements Serializable {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 2919113892981855298L;

	/**
	 * Id field.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = BookStoreConstants.BOOK_ID)
	private Long id;

	/**
	 * ISBN field.
	 */
	@Column(name = BookStoreConstants.ISBN_NUMBER, nullable = false)
	private String isbn;

	/**
	 * TITLE field.
	 */
	@Column(name = BookStoreConstants.TITLE, nullable = false)
	private String title;

	/**
	 * PRICE field.
	 */
	@Column(name = BookStoreConstants.PRICE, nullable = false)
	private Double price;

	/**
	 * COPIES field.
	 */
	@Column(columnDefinition = "integer default 1", name = BookStoreConstants.COPIES)
	private Integer copies;

	/**
	 * Author representing the relationship of many books associated with one author name.
	 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = BookStoreConstants.AUTHOR_ID, foreignKey = @ForeignKey(name = BookStoreConstants.FK_AUTHOR))
	@ToString.Exclude
	private AuthorEntity author;

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
	 * Instance of version column.
	 */
	@Version
	private Integer version;

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
