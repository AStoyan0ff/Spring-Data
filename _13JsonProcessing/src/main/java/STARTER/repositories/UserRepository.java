package STARTER.repositories;

import STARTER.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(
        """
            SELECT u
            FROM User u
            JOIN FETCH u.productsSold p
            JOIN FETCH p.buyer
            WHERE p.buyer IS NOT NULL
            ORDER BY u.lastName, u.firstName
        """)
    List<User> getUsersByProductsSold();
}

