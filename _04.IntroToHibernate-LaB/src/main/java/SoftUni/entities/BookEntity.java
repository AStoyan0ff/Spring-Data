package SoftUni.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "book_entities")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id")
    private Author author;

    public BookEntity() {}

    public BookEntity(String title) {
        this.title = title;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
