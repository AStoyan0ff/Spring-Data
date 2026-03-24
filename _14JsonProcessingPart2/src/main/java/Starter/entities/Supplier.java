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

@Table(name = "suppliers")
public class Supplier extends BaseClass {

    private String name;
    private Boolean usesImportedParts;

    @OneToMany(mappedBy = "supplier")
    private Set<Part> parts = new HashSet<>();
}
