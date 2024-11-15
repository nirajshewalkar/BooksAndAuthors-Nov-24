package com.niraj.database.repositories;

import com.niraj.database.TestDataUtil;
import com.niraj.database.domain.entities.AuthorEntity;
import com.niraj.database.domain.entities.BookEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookEntityDaoImplIntegrationTest {
    private final AuthorRepository authorDao;
    private final BookRepository underTest;

    @Autowired
    public BookEntityDaoImplIntegrationTest(BookRepository underTest, AuthorRepository authorDao) {
        this.underTest = underTest;
        this.authorDao = authorDao;
    }

//    @Test
//    public void testThatBookCanBeCreatedAndRecalled() {
//        Author author = TestDataUtil.createTestAuthor();
//        authorDao.create(author);
//        Book book = TestDataUtil.createTestBook();
//        book.setAuthor_id(author.getId());
//        underTest.create(book);
//        Optional<Book> result = underTest.findOne(book.getIsbn());
//        assertThat(result).isPresent();
//        assertThat(result.get()).isEqualTo(book);
//    }

    @Test
    public void testThatMultipleBookCanBeCreatedAndRecalled() {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthor();

        BookEntity bookEntity = TestDataUtil.createTestBook(authorEntity);
        underTest.save(bookEntity);

        BookEntity bookEntityA = TestDataUtil.createTestBookA(authorEntity);
        underTest.save(bookEntityA);

        BookEntity bookEntityB = TestDataUtil.createTestBookB(authorEntity);
        underTest.save(bookEntityB);

        Iterable<BookEntity> books = underTest.findAll();
        assertThat(books)
                .hasSize(3)
                .containsExactly(bookEntity, bookEntityA, bookEntityB);
    }

    @Test
    public void testThatBookCanBeUpdated() {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthor();

        BookEntity bookEntity = TestDataUtil.createTestBook(authorEntity);
        underTest.save(bookEntity);

        bookEntity.setTitle("UPDATED");
        underTest.save(bookEntity);

        Optional<BookEntity> result = underTest.findById(bookEntity.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookEntity);
    }

    @Test
    public void TestThatBookCanBeDeleted() {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthor();

        BookEntity bookEntity = TestDataUtil.createTestBook(authorEntity);
        underTest.save(bookEntity);

        underTest.deleteById(bookEntity.getIsbn());

        Optional<BookEntity> result = underTest.findById(bookEntity.getIsbn());
        assertThat(result).isEmpty();
    }
}
