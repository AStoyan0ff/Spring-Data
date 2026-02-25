package SoftUni.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "author",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<BookEntity> books = new HashSet<>();

    public Author() {}

    public Author(String name) {
        this.name = name;
    }

    public void addBook(BookEntity book) {
        books.add(book);
        book.setAuthor(this);
    }
}

