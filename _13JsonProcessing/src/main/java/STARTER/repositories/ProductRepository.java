package STARTER.repositories;

import STARTER.DTOs.ProductPriceDTO;
import STARTER.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Query 1 - Products in a range
    @Query("SELECT new STARTER.DTOs.ProductPriceDTO(" +
            "p.name, p.price, " +
                "CONCAT(p.seller.firstName, ' ', p.seller.lastName)) " +
            "FROM Product p " +
            "WHERE p.price " +
            "BETWEEN :min AND :max " +
            "ORDER BY p.price")


    List<ProductPriceDTO> findProductsByDTO(@Param("min") BigDecimal min,
                                  @Param("max") BigDecimal max);
}
