package Starter.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "customers")
public class Customer extends BaseClass {


    private String name;
    private LocalDateTime birthDate;
    private Boolean isYoungDriver;

    @OneToMany(mappedBy = "customer")
    private Set<Sale> sales = new HashSet<>();
}
