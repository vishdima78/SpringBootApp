package vish.dima.crud.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    @NotEmpty(message = "Title should not be empty")
    @Size(min = 3, max = 100, message = "Name should be between 3 and 200 characters")
    private String title;

    @NotEmpty(message = "Author should not be empty")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+",message = "Author should be in this format: Dima Vish")
    @Column(name = "author")
    private String author;

    @Column(name = "year")
    @Min(value = 0, message = "year should be greater than 0")
    @NotNull
    private int year;

    @ManyToOne
    @JoinColumn(name = "person_id" ,referencedColumnName = "id")
    private Person owner;

    @Column(name = "taken_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date takenAt;

    @Transient
    private boolean expired;

    public Date getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(Date takenAt) {
        this.takenAt = takenAt;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotEmpty(message = "Title should not be empty") @Size(min = 3, max = 100, message = "Name should be between 3 and 200 characters") String getTitle() {
        return title;
    }

    public void setTitle(@NotEmpty(message = "Title should not be empty") @Size(min = 3, max = 100, message = "Name should be between 3 and 200 characters") String title) {
        this.title = title;
    }

    public @NotEmpty(message = "Author should not be empty") @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+", message = "Author should be in this format: Dima Vish") String getAuthor() {
        return author;
    }

    public void setAuthor(@NotEmpty(message = "Author should not be empty") @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+", message = "Author should be in this format: Dima Vish") String author) {
        this.author = author;
    }

    @Min(value = 0, message = "year should be greater than 0")
    @NotNull
    public int getYear() {
        return year;
    }

    public void setYear(@Min(value = 0, message = "year should be greater than 0") @NotNull int year) {
        this.year = year;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Book() {
    }

    public Book(String title, int year) {
        this.title = title;
        this.year = year;
    }
}