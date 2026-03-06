package bg.softuni.DTO;

public class AuthorSummaryDTO {

    private final String firstName;
    private final String lastName;
    private final Integer booksCount;


    public AuthorSummaryDTO(String firstName, String lastName, Integer booksCount) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.booksCount = booksCount;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getBooksCount() {
        return booksCount;
    }
}
