package softuni.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "towns")
public class Town {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String country;

    public Town() {}

    public Town(String name, String country) {

        this.name = name;
        this.country = country;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getCountry() { return country; }

    public void setCountry(String country) { this.country = country; }

}
