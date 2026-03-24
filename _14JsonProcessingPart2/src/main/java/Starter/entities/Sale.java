package Starter.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "sales")
public class Sale extends BaseClass {

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "discount_percentage")
    private double discountPercentage;

    // Calculate price after discount

    public double getPriceAfterDiscount() {
        if (car == null || customer == null) {
            return 0.0;
        }

        double basePrice = car.getPrice();

        double totalDiscount = discountPercentage;

        if (customer.getIsYoungDriver()) {
            totalDiscount += 5.0; // добавяме допълнителни 5% за млад шофьор
        }

        return basePrice * (1 - totalDiscount / 100.0);
    }
}
