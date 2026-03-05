package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.entities.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer>
{}
