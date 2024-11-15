package com.niraj.database;

import com.niraj.database.domain.dto.AuthorDto;
import com.niraj.database.domain.dto.BookDto;
import com.niraj.database.domain.entities.AuthorEntity;
import com.niraj.database.domain.entities.BookEntity;

public final class TestDataUtil {
    private TestDataUtil() {
    }

    public static AuthorEntity createTestAuthor() {
        return AuthorEntity.builder()
                .id(1L)
                .name("JK Rowling")
                .age(67)
                .build();
    }

    public static AuthorEntity createTestAuthorA() {
        return AuthorEntity.builder()
                .id(2L)
                .name("Jon Jones")
                .age(38)
                .build();
    }

    public static AuthorEntity createTestAuthorB() {
        return AuthorEntity.builder()
                .id(3L)
                .name("Steve Jobs")
                .age(51)
                .build();
    }

    public static AuthorDto createTestAuthorDto() {
        return AuthorDto.builder()
                .id(1L)
                .name("JK Rowling")
                .age(67)
                .build();
    }

    public static BookDto createTestBookDto(AuthorDto authorEntity) {
        return BookDto.builder()
                .isbn("978-1-2345-6789-0")
                .title("Harry Potter")
                .author(authorEntity)
                .build();
    }

    public static BookEntity createTestBook(AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("978-1-2345-6789-0")
                .title("Harry Potter")
                .authorEntity(authorEntity)
                .build();
    }

    public static BookEntity createTestBookA(AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("977-1-2345-6789-0")
                .title("Harry Potter")
                .authorEntity(authorEntity)
                .build();
    }

    public static BookEntity createTestBookB(AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("976-1-2345-6789-0")
                .title("Harry Potter")
                .authorEntity(authorEntity)
                .build();
    }
}
