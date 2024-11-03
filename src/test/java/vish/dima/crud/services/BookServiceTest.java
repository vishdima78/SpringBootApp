package vish.dima.crud.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import vish.dima.crud.models.Book;
import vish.dima.crud.models.Person;
import vish.dima.crud.repositories.BooksRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @InjectMocks
    private BookService bookService;
    @Mock
    private BooksRepository booksRepository;

    @Test
    void findWithPagination_ShouldReturnSortedBooks_WhenSortByYearIsTrue() {
        Book book1 = new Book("A", 2002);
        Book book2 = new Book("B", 2003);
        List<Book> books = Arrays.asList(book1, book2);

        when(booksRepository.findAll(PageRequest.of(0, 10, Sort.by("year")))).thenReturn(new PageImpl<>(books));

        List<Book> resultWithSort = bookService.findWithPagination(0, 10, true);

        //Test sort
        assertNotNull(resultWithSort, "result should not be null");
        assertEquals(2, resultWithSort.size(), "Expected to get 2 books");
        assertEquals("A", resultWithSort.get(0).getTitle(), "First books should be A");
        assertEquals("B", resultWithSort.get(1).getTitle(), "First books should be B");
        verify(booksRepository).findAll(PageRequest.of(0, 10, Sort.by("year")));

    }

    @Test
    void findWithPagination_ShouldReturnSortedBooks_WhenSortByYearIsFalse() {
        Book book1 = new Book("Book A", 2021);
        Book book2 = new Book("Book B", 2020);
        List<Book> books = Arrays.asList(book1, book2);


        when(booksRepository.findAll(PageRequest.of(0, 10))).thenReturn(new PageImpl<>(books));


        List<Book> result = bookService.findWithPagination(0, 10, false);


        assertNotNull(result, "Expected result should not be null");
        assertEquals(2, result.size(), "Expected to get 2 books");
        assertEquals("Book A", result.get(0).getTitle(), "First book should be Book A");
        assertEquals("Book B", result.get(1).getTitle(), "Second book should be Book B");
        verify(booksRepository).findAll(PageRequest.of(0, 10)); // Проверка вызова
    }

    @Test
    void setNullOwner_ShouldSetOwnerAndTakenAtToNull_WhenBookExists() {
        Book book = new Book();
        book.setOwner(new Person("Test", 2022));
        book.setTakenAt(new Date());

        when(booksRepository.findById(1)).thenReturn(Optional.of(book));

        bookService.setNullOwner(1);

        assertNull(book.getOwner());
        assertNull(book.getTakenAt());
        verify(booksRepository).findById(1);
    }

    @Test
    void setNullOwner_ShouldNotModifyBook_WhenBookDoesNotExist () {
        when(booksRepository.findById(1)).thenReturn(Optional.empty());

        bookService.setNullOwner(1);

        verify(booksRepository).findById(1);
    }

    @Test
    void setOwner_ShouldSetOwnerAndTakenAt_WhenBookExists () {
        Book book = new Book();
        Person person = new Person();
         when(booksRepository.findById(1)).thenReturn(Optional.of(book));
         bookService.setNewOwner(1,person);
        assertEquals(person,book.getOwner());
        verify(booksRepository).findById(1);
    }

    @Test
    void setNewOwner_ShouldNotChangeOwner_WhenBookDoesNotExist() {
        Person newOwner = new Person(); // Новый владелец


        when(booksRepository.findById(1)).thenReturn(Optional.empty());

        // Вызываем метод
        bookService.setNewOwner(1, newOwner);


        verify(booksRepository).findById(1);


    }

}
