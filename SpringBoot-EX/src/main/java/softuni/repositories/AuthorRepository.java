package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.entities.Author;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query(
        """
        SELECT DISTINCT a
        FROM Author a
        JOIN a.books b
        WHERE b.releaseDate < :date
    """)
    List<Author> findAuthorsWithBookBefore(@Param("date") LocalDate date);
}
