package bg.example.web.controllers;

import bg.example.entities.Person;
import bg.example.services.PersonService;
import bg.example.web.DTOs.PersonCreateDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    private final PersonService personService;

    @GetMapping("/api/people")
    public List<Person> getAllPeople() {

        return personService.findAll();
    }

    @GetMapping("/api/people/{id}")
        public Person getPersonById(@PathVariable Long id) {
            return personService.findById(id);
    }

    @GetMapping("/api/people")
    public Person create(@RequestBody PersonCreateDTO data) {
        return personService.create(data);
    }
}
