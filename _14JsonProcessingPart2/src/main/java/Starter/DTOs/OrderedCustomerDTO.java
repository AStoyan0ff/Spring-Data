package Starter.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderedCustomerDTO { // Query 1 – Ordered Customers

    private Long id;
    private String name;
    private LocalDateTime birthDate;
    private boolean isYoungDriver;
    private List<Object> sales;
}
