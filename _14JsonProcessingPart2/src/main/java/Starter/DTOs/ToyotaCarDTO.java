package Starter.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ToyotaCarDTO {

    private Long id;
    private String make;
    private String model;
    private Long traveledDistance;
}
