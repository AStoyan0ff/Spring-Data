package SoftUni.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detail_id")
    private BookDetail bookDetail;

    public Book() {}

    public Book(String title, BookDetail detail) {
        this.title = title;
        this.bookDetail = detail;
        detail.setBook(this);
    }
}
