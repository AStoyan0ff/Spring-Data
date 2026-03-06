package bg.softuni.services;

import bg.softuni.DTO.AuthorDTO;
import bg.softuni.DTO.AuthorSummaryDTO;
import bg.softuni.entities.Author;
import bg.softuni.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author createAuthor(AuthorDTO input) {

        Author author = new Author();
        author.setFirstName(input.getFirstName());
        author.setLastName(input.getLastName());

        return authorRepository.save(author);
    }

    @Override
    public List<Author> findAuthorsBefore(int year) {

        LocalDate date = LocalDate.of(year, 1, 1);
        return authorRepository.findDistinctByBooksReleaseDateLessThan(date);
    }

    @Override
    public List<AuthorSummaryDTO> getSummary() {
        return authorRepository.getSummary();
    }
}
