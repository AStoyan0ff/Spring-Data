package softuni.entities;

import jakarta.persistence.*;
import softuni.enums.AgeRestriction;
import softuni.enums.EditionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String title;

    @Column(length = 255)
    private String description;

    @Enumerated(EnumType.ORDINAL)
    private EditionType editionType;

    private BigDecimal price;
    private int copies;
    private LocalDate releaseDate;

    @Enumerated(EnumType.ORDINAL)
    private AgeRestriction ageRestriction;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToMany
    @JoinTable(
        name = "books_categories",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;

    public Book() {}

    public Book(
        String title, EditionType editionType, BigDecimal price,
        LocalDate releaseDate, AgeRestriction ageRestriction,
        Author author, Set<Category> categories, int copies) {

            this.title = title;
            this.editionType = editionType;
            this.price = price;
            this.releaseDate = releaseDate;
            this.ageRestriction = ageRestriction;
            this.author = author;
            this.categories = categories;
            this.copies = copies;
        }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public EditionType getEditionType() { return editionType; }

    public void setEditionType(EditionType editionType) { this.editionType = editionType; }

    public BigDecimal getPrice() { return price; }

    public void setPrice(BigDecimal price) { this.price = price; }

    public int getCopies() { return copies; }

    public void setCopies(int copies) { this.copies = copies; }

    public LocalDate getReleaseDate() { return releaseDate; }

    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }

    public AgeRestriction getAgeRestriction() { return ageRestriction; }

    public void setAgeRestriction(AgeRestriction ageRestriction) { this.ageRestriction = ageRestriction; }

    public Author getAuthor() { return author; }

    public void setAuthor(Author author) { this.author = author; }

    public Set<Category> getCategories() { return categories; }

    public void setCategories(Set<Category> categories) { this.categories = categories; }
}

