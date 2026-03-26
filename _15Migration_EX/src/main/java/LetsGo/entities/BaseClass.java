package LetsGo.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@MappedSuperclass
public class BaseClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
