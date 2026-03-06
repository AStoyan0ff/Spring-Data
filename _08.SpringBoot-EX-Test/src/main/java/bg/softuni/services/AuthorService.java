package bg.softuni.services;

import bg.softuni.DTO.AuthorDTO;
import bg.softuni.DTO.AuthorSummaryDTO;
import bg.softuni.entities.Author;

import java.util.List;

public interface AuthorService {

    Author createAuthor(AuthorDTO input);
    List<Author> findAuthorsBefore(int year);
    List<AuthorSummaryDTO> getSummary();
}
