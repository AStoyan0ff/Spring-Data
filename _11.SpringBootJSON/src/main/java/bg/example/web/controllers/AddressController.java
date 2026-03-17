package bg.example.web.controllers;

import bg.example.entities.Address;
import bg.example.repositories.AddressRepository;
import bg.example.services.AddressService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/api/addresses")
    public List<Address> getAllAddresses() {
        return addressService.findAll();
    }

    @PostMapping("/api/addresses")
    public Address create(@RequestBody Address addressData) {
        return addressService.create(addressData);
    }
}
