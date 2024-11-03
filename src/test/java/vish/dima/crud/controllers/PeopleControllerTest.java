package vish.dima.crud.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import vish.dima.crud.models.Book;
import vish.dima.crud.models.Person;
import vish.dima.crud.services.PeopleService;
import vish.dima.crud.util.PersonValidator;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PeopleControllerTest {
    @InjectMocks
    private PeopleController peopleController;

    @Mock
    private PeopleService peopleService;

    @Mock
    private Model model;

    @Mock
    private PersonValidator personValidator; // Мок валидатора

    @Mock
    private BindingResult bindingResult;

    Person person = new Person();


    @Test
    void index_ShouldAddPeopleToModelAndReturnViewName() {
        List<Person> people = Arrays.asList(
                new Person(),
                new Person()
        );

        when(peopleService.findAll()).thenReturn(people);

        String viewName = peopleController.index(model);

        verify(peopleService).findAll();

        verify(model).addAttribute("people", people);

        assertEquals("people/index", viewName);
    }

    @Test
    void show_ShouldAddPersonAndBooksToModelAndReturnViewName() {
        int testId = 1;
        List<Book> books = Arrays.asList(
                new Book(),
                new Book()
        );

        when(peopleService.findOne(testId)).thenReturn(person);
        when(peopleService.getBooksForOwnerId(testId)).thenReturn(books);

        String viewName = peopleController.show(testId, model);

        verify(peopleService).findOne(testId);
        verify(peopleService).getBooksForOwnerId(testId);

        verify(model).addAttribute("person", person);
        verify(model).addAttribute("books", books);

        assertEquals("people/show", viewName);
    }

    @Test
    void newPerson_ShouldSaveNewPerson() {

        String viewName = peopleController.newPerson(person);
        assertEquals("people/new", viewName);
    }

    @Test
    void create_ShouldCreateNewPerson(){

        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = peopleController.create(person,bindingResult);

        verify(personValidator).validate(person,bindingResult);

        verify(peopleService).save(person);

        assertEquals("redirect:/people",viewName);

    }

    @Test
    void create_ShouldReturnNewView_WhenPersonHasErrors(){

        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = peopleController.create(person,bindingResult);

        verify(personValidator).validate(person,bindingResult);

        verify(peopleService, never()).save(person);

        assertEquals("people/new",viewName);

    }

    @Test
    void edit_ShouldBeEditPerson () {
        int testId = 1;

        when(peopleService.findOne(testId)).thenReturn(person);
        String viewName = peopleController.edit(model,testId);

        verify(peopleService).findOne(testId);
        verify(model).addAttribute("person", person);

        assertEquals("people/edit",viewName);
    }

    @Test
    void update_ShouldUpdatePerson_WhenBindingResultTrue () {

        int testId = 1;
        when(bindingResult.hasErrors()).thenReturn(false);
        String viewName = peopleController.update(person,bindingResult,testId);

        verify(personValidator).validate(person,bindingResult);
        verify(peopleService).update(person,testId);

        assertEquals("redirect:/people",viewName);
    }

    @Test
    void delete_ShouldDeletePerson () {
        int testId = 1;
        String viewName = peopleController.delete(testId);
        verify(peopleService).delete(testId);

        assertEquals("redirect:/people",viewName);
    }
}