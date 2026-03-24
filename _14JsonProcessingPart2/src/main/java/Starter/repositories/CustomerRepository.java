package Starter.repositories;

import Starter.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(
            "SELECT c " +
            "FROM Customer c " +
            "LEFT JOIN FETCH c.sales " +
            "ORDER BY c.birthDate ASC, c.isYoungDriver ASC"
    )

    List<Customer> findAllOrdered();
}
