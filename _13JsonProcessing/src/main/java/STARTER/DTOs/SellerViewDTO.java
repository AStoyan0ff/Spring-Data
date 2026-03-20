package STARTER.DTOs;

import java.util.List;

public record SellerViewDTO(String firstName,
                            String lastName,
                            List<SoldProductViewDTO>  soldProducts)
{}
