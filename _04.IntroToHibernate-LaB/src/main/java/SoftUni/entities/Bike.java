package SoftUni.entities;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("BIKE")
public class Bike extends Vehicle {

    private int loadCapacity;

    public Bike() {}

    public Bike(int loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public int getLoadCapacity() {
        return loadCapacity;
    }
}
