package LetsGo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "technologies")
public class Technology extends BaseClass {

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String category;

    @ManyToMany(mappedBy = "technologies")
    private Set<Developer> developers = new HashSet<>();

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
