package SPRING.repositories;

import SPRING.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query(
    """
        SELECT COUNT(d)
        FROM Department d
        JOIN d.employees e
        WHERE d.name = :departmentName
    """)
    int countEmployeesByDepartmentName(String departmentName);
}
