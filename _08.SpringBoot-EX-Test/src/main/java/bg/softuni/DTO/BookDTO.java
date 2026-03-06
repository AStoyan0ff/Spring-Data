package bg.softuni.DTO;

import bg.softuni.enums.AgeRestriction;
import bg.softuni.enums.EditionType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookDTO {

    private final String title;
    private final Long copies;
    private final BigDecimal price;
    private final EditionType editionType;
    private final LocalDate releaseDate;
    private final AgeRestriction  ageRestriction;


    public BookDTO(String title, Long copies, BigDecimal price, EditionType editionType, LocalDate releaseDate, AgeRestriction ageRestriction) {
        this.title = title;
        this.copies = copies;
        this.price = price;
        this.editionType = editionType;
        this.releaseDate = releaseDate;
        this.ageRestriction = ageRestriction;
    }

    public String getTitle() {
        return title;
    }

    public Long getCopies() {
        return copies;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public EditionType getEditionType() {
        return editionType;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }
}
