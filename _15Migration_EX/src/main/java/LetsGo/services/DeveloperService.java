package LetsGo.services;

import LetsGo.entities.Developer;
import LetsGo.repositories.DeveloperRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeveloperService {

    private final DeveloperRepository developerRepository;

    public DeveloperService(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    public List<Developer> getAllDevelopers() {
        return developerRepository.findByIsActiveTrue();
    }
}
