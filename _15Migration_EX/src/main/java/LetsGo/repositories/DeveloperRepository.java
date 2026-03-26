package LetsGo.repositories;

import LetsGo.entities.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {

    List<Developer> findByIsActiveTrue();
}
