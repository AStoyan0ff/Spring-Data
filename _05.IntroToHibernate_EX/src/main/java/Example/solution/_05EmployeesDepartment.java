package Example.solution;

import Example.entities.Employee;
import jakarta.persistence.*;
import java.util.List;

public class _05EmployeesDepartment {
    static void main() {

        EntityManagerFactory mf = Persistence.createEntityManagerFactory("hibernate-exercise");
        EntityManager em = mf.createEntityManager();

        getInfo(em);

        em.close();
        mf.close();
    }

    private static void getInfo(EntityManager em) {
        TypedQuery<Employee> query = em.createQuery(
            """
                SELECT e
                FROM Employee AS e
                WHERE e.department.name = :dn
                ORDER BY e.salary, e.id
            """,
            Employee.class);

        query.setParameter("dn", "Research and Development");
        List<Employee> employeeList=  query.getResultList();

        for (Employee e: employeeList) {

            System.out.printf("%s %s from %s -> $%.2f%n",
                e.getFirstName(),
                e.getLastName(),
                e.getDepartment().getName(),
                e.getSalary());
        }
    }
}
