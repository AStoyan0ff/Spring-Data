package SoftUni.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "book_details")
public class BookDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String summary;

    @OneToOne(mappedBy = "bookDetail")
    private Book book;

    public BookDetail() {}

    public BookDetail(String summary) {
        this.summary = summary;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
