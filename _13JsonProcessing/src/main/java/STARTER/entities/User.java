package STARTER.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")

public class User extends BaseClass {

    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Size(min = 3)
    @Column(name = "last_name", nullable = false)
    private String lastName;


    @Column(name = "age")
    private Integer age;

    @OneToMany(mappedBy = "buyer")
    private Set<Product> productsBought = new HashSet<>();

    @OneToMany(mappedBy = "seller")
    private Set<Product> productsSold = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "users_friends",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "friend_id"))

    private Set<User> friends = new HashSet<>();
}
