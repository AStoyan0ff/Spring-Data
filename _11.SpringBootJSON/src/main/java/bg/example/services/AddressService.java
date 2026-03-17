package bg.example.services;

import bg.example.entities.Address;
import bg.example.repositories.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;

    }

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public Address create(Address addressData) {
        return addressRepository.save(addressData);
    }
}
