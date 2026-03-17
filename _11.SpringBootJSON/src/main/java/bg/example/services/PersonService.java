package bg.example.services;

import bg.example.entities.Address;
import bg.example.entities.Person;
import bg.example.repositories.AddressRepository;
import bg.example.repositories.PersonRepository;
import bg.example.web.DTOs.PersonCreateDTO;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;

    public PersonService(PersonRepository personRepository, AddressRepository addressRepository) {
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    public Person create(PersonCreateDTO data) {
        Person person = mapDTO(data);

        Optional<Address> temp = addressRepository.findById(data.addressId());

        if (temp.isEmpty()) return null;

        person.setAddress(temp.get());
        return personRepository.save(person);
    }

    private static Person mapDTO(PersonCreateDTO data) {
        Person person = new Person();

        person.setFirstName(data.firstName());
        person.setLastName(data.lastName());
        person.setEmail(data.email());
        person.setAge(data.age());

        return person;
    }
}
