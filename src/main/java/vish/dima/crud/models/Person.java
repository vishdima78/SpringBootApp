package vish.dima.crud.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
@Table(name = "Person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name_person")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3, max = 200, message = "Name should be between 3 and 200 characters")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+", message = "Your name should be in this format: Dima Vish Maksimovich")
    private String name;

    @Column(name = "year_of_birth")
    @Min(value = 1900, message = "birthdayYear should be greater than 1900")
    @NotNull
    private int year;


    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", books=" + books +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotEmpty(message = "Name should not be empty") @Size(min = 3, max = 200, message = "Name should be between 3 and 200 characters") @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+", message = "Your name should be in this format: Dima Vish Maksimovich") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "Name should not be empty") @Size(min = 3, max = 200, message = "Name should be between 3 and 200 characters") @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+", message = "Your name should be in this format: Dima Vish Maksimovich") String name) {
        this.name = name;
    }

    @Min(value = 1900, message = "birthdayYear should be greater than 1900")
    @NotNull
    public int getYear() {
        return year;
    }

    public void setYear(@Min(value = 1900, message = "birthdayYear should be greater than 1900") @NotNull int year) {
        this.year = year;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Person() {
    }

    public Person(String name, int year) {
        this.name = name;
        this.year = year;
    }
}