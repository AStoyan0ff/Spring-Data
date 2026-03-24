package Starter.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "cars")
public class Car extends BaseClass {

    private String make;
    private String model;
    private Long traveledDistance;

    @ManyToMany
    @JoinTable(
        name = "parts_cars",
        joinColumns = @JoinColumn(name = "car_id"),
        inverseJoinColumns = @JoinColumn(name = "part_id")
    )
    private Set<Part> parts = new HashSet<>();

    // Calculate total price based on parts

    public double getPrice() {
        return parts.stream()
            .mapToDouble(Part::getPrice)
            .sum();
    }
}
