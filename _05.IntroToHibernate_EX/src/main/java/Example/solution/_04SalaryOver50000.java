package Example.solution;

import Example.entities.Employee;
import jakarta.persistence.*;
import java.util.List;

public class _04SalaryOver50000 {
    static void main() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-exercise");
        EntityManager em = emf.createEntityManager();

        salaryOver50000(em);

        em.close();
        emf.close();
    }

    private static void salaryOver50000(EntityManager em) {

        TypedQuery<Employee> query = em.createQuery(
            """
                SELECT e
                FROM Employee e
                WHERE e.salary > 50000
            """,
            Employee.class);

        List<Employee> resultList = query.getResultList();

        for (Employee e : resultList) {

            System.out.printf("%s%s%s%n",
                e.getFirstName(), " " +
                e.getLastName(), " -> " +
                e.getSalary());
        }
    }
}
