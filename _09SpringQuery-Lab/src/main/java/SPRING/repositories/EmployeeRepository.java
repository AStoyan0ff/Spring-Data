package SPRING.repositories;

import SPRING.entities.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Query methods 1

    List<Employee> findByLastName(String lastName);

    // Query methods 2

    List<Employee> findBySalaryBetweenOrderBySalaryDesc(
        BigDecimal salaryAfter,
        BigDecimal salaryBefore);

    // Query methods 3

    List<Employee> findByEmailContainingAndDepartmentNameOrderByHireDateAsc(
        String email, String departmentName);

    // Query methods 4 - JPQL

    @Query(
            """
                SELECT e
                FROM Employee e
                JOIN e.projects p
                WHERE p.name = :projectName
            """) // @param(name = "projectName") - Тест с параметър

    List<Employee> findByProjectsName(String projectName);

    @Query
        ("""
            UPDATE Employee e
            SET e.salary = e.salary * (1.0 + :percent)
            WHERE e.department.name = :departmentName
        """)

    @Modifying
    @Transactional
    int  updateSalaryByDepartmentName(String departmentName, double percent);

    @Query(
        """
            SELECT e
            FROM Employee e
            JOIN e.projects p
            WHERE CONCAT(e.firstName, e.lastName)
            LIKE %:departmentName%
            AND SIZE(e.projects) > :projectNumber
        """)

    List<Employee> findByNames(String departmentName, int projectNumber);
}
