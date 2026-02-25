package Example.solution;

import Example.entities.Employee;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class _10IncreaseSalaries {
    static void main() {

        EntityManagerFactory mf = Persistence.createEntityManagerFactory("hibernate-exercise");
        EntityManager em = mf.createEntityManager();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine(); // SA

        printEmployees(em, input);

        em.close();
    }

    private static void printEmployees(EntityManager em, String input) {

        List<Employee> employees = em.createQuery(
                """
                    FROM Employee e
                    WHERE e.firstName LIKE :pattern
                    ORDER BY e.firstName, e.lastName
                """,
                Employee.class
            )
            .setParameter("pattern", input + "%")
            .getResultList();

        for (Employee e : employees) {

            System.out.printf("%s %s - %s - ($%.2f)%n",
                e.getFirstName(),
                e.getLastName(),
                e.getJobTitle(),
                e.getSalary()
            );
        }
    }
}