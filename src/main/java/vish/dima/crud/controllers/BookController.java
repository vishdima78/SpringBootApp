package vish.dima.crud.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vish.dima.crud.models.Book;
import vish.dima.crud.models.Person;
import vish.dima.crud.services.BookService;
import vish.dima.crud.services.PeopleService;
import vish.dima.crud.util.BookValidator;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private final PeopleService peopleService;
    private final BookService bookService;
    private final BookValidator bookValidator;

    //private final PersonDAO personDAO;
    @Autowired
    public BookController(PeopleService peopleService, BookService bookService, BookValidator bookValidator) {
        this.peopleService = peopleService;
        this.bookService = bookService;
        this.bookValidator = bookValidator;
    }


    @GetMapping()
    public String index(Model model, @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "books_per_page", required = false) Integer booksPerPage,
                        @RequestParam(value = "sort_by_year", required = false) boolean sortByYear) {

        if (page == null || booksPerPage == null)
            model.addAttribute("books", bookService.findAll(sortByYear));

        else
            model.addAttribute("books", bookService.findWithPagination(page, booksPerPage, sortByYear));

        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookService.findOne(id));
        Optional<Person> bookOwner = bookService.getPersonForId(id);

        if (bookOwner.isPresent()) model.addAttribute("owner", bookOwner.get());
        else model.addAttribute("people", peopleService.findAll());
        return "books/show";
    }

    @PatchMapping("/{id}/addBook")
    public String addBook(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        bookService.setNewOwner(id, person);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/deleteBook")
    public String setNullOwnerBook(@PathVariable("id") int id) {
        bookService.setNullOwner(id);
        return "redirect:/books/" + id;
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) return "books/new";
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookService.findOne(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) return "books/edit";
        bookService.update(book, id);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/search")
    public String searchPage() {
        return "books/search";
    }

    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam("query") String query) {
        model.addAttribute("books", bookService.searchByTitle(query));
        return "books/search";
    }
}