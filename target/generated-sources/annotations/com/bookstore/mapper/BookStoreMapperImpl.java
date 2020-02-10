package com.bookstore.mapper;

import com.bookstore.entity.AuthorEntity;
import com.bookstore.entity.BookEntity;
import com.bookstore.model.BookStoreTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-02-10T12:07:54+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_221 (Oracle Corporation)"
)
@Component
public class BookStoreMapperImpl implements BookStoreMapper {

    @Override
    public BookEntity bookStoreTOToBookEntity(BookStoreTO bookStoreTO) {
        if ( bookStoreTO == null ) {
            return null;
        }

        BookEntity bookEntity = new BookEntity();

        bookEntity.setAuthor( bookStoreTOToAuthorEntity( bookStoreTO ) );
        bookEntity.setIsbn( bookStoreTO.getIsbn() );
        bookEntity.setTitle( bookStoreTO.getTitle() );
        bookEntity.setPrice( bookStoreTO.getPrice() );
        bookEntity.setCopies( bookStoreTO.getCopies() );

        return bookEntity;
    }

    @Override
    public BookStoreTO bookEntityToBookStoreTO(BookEntity bookEntity) {
        if ( bookEntity == null ) {
            return null;
        }

        BookStoreTO bookStoreTO = new BookStoreTO();

        String authorName = bookEntityAuthorAuthorName( bookEntity );
        if ( authorName != null ) {
            bookStoreTO.setAuthorName( authorName );
        }
        bookStoreTO.setIsbn( bookEntity.getIsbn() );
        bookStoreTO.setTitle( bookEntity.getTitle() );
        if ( bookEntity.getPrice() != null ) {
            bookStoreTO.setPrice( bookEntity.getPrice() );
        }
        bookStoreTO.setCopies( bookEntity.getCopies() );

        return bookStoreTO;
    }

    @Override
    public List<BookStoreTO> bookStoreEntityListToBookStoreTOList(List<BookEntity> bookEntityList) {
        if ( bookEntityList == null ) {
            return null;
        }

        List<BookStoreTO> list = new ArrayList<BookStoreTO>( bookEntityList.size() );
        for ( BookEntity bookEntity : bookEntityList ) {
            list.add( bookEntityToBookStoreTO( bookEntity ) );
        }

        return list;
    }

    protected AuthorEntity bookStoreTOToAuthorEntity(BookStoreTO bookStoreTO) {
        if ( bookStoreTO == null ) {
            return null;
        }

        AuthorEntity authorEntity = new AuthorEntity();

        authorEntity.setAuthorName( bookStoreTO.getAuthorName() );

        return authorEntity;
    }

    private String bookEntityAuthorAuthorName(BookEntity bookEntity) {
        if ( bookEntity == null ) {
            return null;
        }
        AuthorEntity author = bookEntity.getAuthor();
        if ( author == null ) {
            return null;
        }
        String authorName = author.getAuthorName();
        if ( authorName == null ) {
            return null;
        }
        return authorName;
    }
}
