package STARTER.DTOs;

import java.math.BigDecimal;

public record SoldProductViewDTO(String name,
                                 BigDecimal price,
                                 String buyerFirstName,
                                 String buyerLastNam )
{}

