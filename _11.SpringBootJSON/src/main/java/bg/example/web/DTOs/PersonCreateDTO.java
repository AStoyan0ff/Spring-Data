package bg.example.web.DTOs;

public record PersonCreateDTO(String firstName,
                              String lastName,
                              String email,
                              Integer age,
                              Long addressId)
{}



