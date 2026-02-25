package Example.solution;

import jakarta.persistence.*;

import java.util.Scanner;

public class _03ContainsEmployee {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        String[] data = scanner.nextLine().split("\\s+");
        String firstName = data[0];
        String lastName = data[1];

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-exercise");
        EntityManager em = emf.createEntityManager();

        findEmployee(em, firstName, lastName);

        em.close();
        emf.close();
        scanner.close();
    }

    private static void findEmployee(EntityManager em, String firstName, String lastName) {
        TypedQuery<Long> query = em.createQuery(
            """ 
                SELECT COUNT(e)
                FROM Employee AS e
                WHERE e.firstName = :fn
                AND e.lastName = :ln
            """,
                Long.class);

        query.setParameter("fn", firstName);
        query.setParameter("ln", lastName);

        Long count = query.getSingleResult();

        if (count == 0) {
            System.out.println("No");

        } else {
            System.out.println("Yes");
        }
    }
}
