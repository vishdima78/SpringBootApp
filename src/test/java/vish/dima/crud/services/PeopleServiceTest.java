package vish.dima.crud.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import vish.dima.crud.models.Book;
import vish.dima.crud.models.Person;
import vish.dima.crud.repositories.BooksRepository;
import vish.dima.crud.repositories.PeopleRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PeopleServiceTest {
    @InjectMocks
    private PeopleService peopleService;
    @Mock
    private PeopleRepository peopleRepository;
    Person person = new Person("dimas", 1920);
    Person person2 = new Person("liza", 2005);

    @InjectMocks
    private BookService bookService;
    @Mock
    private BooksRepository booksRepository;

    @Test
    void findAllTest() {
        when(peopleRepository.findAll()).thenReturn(List.of(person, person2));

        List<Person> res = peopleService.findAll();

        Assertions.assertEquals(List.of(person, person2), res);
    }

    @Test
    void findOneTest() {
        when(peopleRepository.findById(person.getId())).thenReturn(Optional.of(person));

        Person result = peopleService.findOne(person.getId());


        Assertions.assertNotNull(result, "Expected person to be present");
        Assertions.assertEquals(person, result, "Returned person should match the original");
    }

    @Test
    void showTest() {
        when(peopleRepository.findByName(person.getName())).thenReturn(Optional.of(person));

        Optional<Person> res = peopleService.show(person.getName());

        Assertions.assertEquals(Optional.of(person),res);
    }

    @Test
    void getBooksForOwnerId_ShouldMarkBooksAsExpired_WhenTakenMoreThan10DaysAgo () {
        Date takenAt = new Date(System.currentTimeMillis() - 864000001L);
        Book book = new Book();
        book.setTakenAt(takenAt);
        List<Book> books = new ArrayList<>();
        books.add(book);

        Person ownerBook = new Person();
        ownerBook.setBooks(books);

        when(peopleRepository.findById(1)).thenReturn(Optional.of(ownerBook));

        List<Book> resultBooks = peopleService.getBooksForOwnerId(1);

        assertFalse(resultBooks.isEmpty(), "Expected non-empty list of books");
        assertTrue(resultBooks.get(0).isExpired(),"Expected the book to be marked as expired");
    }

    @Test
    void getBooksForOwnerId_ShouldReturnEmptyList_WhenPersonNotFound() {
        // Настройка мока для случая, когда person не найден
        when(peopleRepository.findById(1)).thenReturn(Optional.empty());

        List<Book> resultBooks = peopleService.getBooksForOwnerId(1);

        assertTrue(resultBooks.isEmpty(), "Expected empty list when person not found");
    }
}
