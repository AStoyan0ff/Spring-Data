package Starter.repositories;

import Starter.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    // Query 3 – Local Suppliers
    @Query(
            "SELECT s " +
            "FROM Supplier s " +
            "WHERE s.usesImportedParts = false"
    )

    List<Supplier> findAllLocalSuppliers();
}
