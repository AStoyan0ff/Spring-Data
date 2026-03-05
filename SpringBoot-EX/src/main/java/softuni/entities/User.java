package softuni.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    private LocalDateTime registeredOn;
    private LocalDateTime lastTimeLoggedIn;

    private int age;
    private boolean isDeleted;

    private String firstName;
    private String lastName;

    @ManyToOne
    private Town bornTown;

    @ManyToOne
    private Town livingTown;

    @ManyToMany
    private Set<User> friends;

    @OneToMany(mappedBy = "owner")
    private Set<Album> albums;

    public User() {}

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public LocalDateTime getRegisteredOn() { return registeredOn; }

    public void setRegisteredOn(LocalDateTime registeredOn) { this.registeredOn = registeredOn; }

    public LocalDateTime getLastTimeLoggedIn() { return lastTimeLoggedIn; }

    public void setLastTimeLoggedIn(LocalDateTime lastTimeLoggedIn) { this.lastTimeLoggedIn = lastTimeLoggedIn; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public boolean isDeleted() { return isDeleted; }

    public void setDeleted(boolean deleted) { isDeleted = deleted; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public Town getBornTown() { return bornTown; }

    public void setBornTown(Town bornTown) { this.bornTown = bornTown; }

    public Town getLivingTown() { return livingTown; }

    public void setLivingTown(Town livingTown) { this.livingTown = livingTown; }

    public Set<User> getFriends() { return friends; }

    public void setFriends(Set<User> friends) { this.friends = friends; }

    public Set<Album> getAlbums() { return albums; }

    public void setAlbums(Set<Album> albums) { this.albums = albums; }
}
