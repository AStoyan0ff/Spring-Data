package Example.solution;

import Example.entities.Address;
import jakarta.persistence.*;

import java.util.Scanner;

public class _06AddNewAddress {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        String lastName = scanner.next();

        EntityManagerFactory mf = Persistence.createEntityManagerFactory("hibernate-exercise");
        EntityManager em = mf.createEntityManager();

        Address currAddress = new Address();
        currAddress.setText("Boris 26");

        int currEmployees = 0;

        updateEmployee(em, currAddress, lastName, currEmployees);
        scanner.close();
    }

    private static void updateEmployee(EntityManager em, Address currAddress, String lastName, int currEmployees) {
        em.getTransaction().begin();

        try {
            em.persist(currAddress);
            Query query = em.createQuery(
                """
                    UPDATE Employee e
                    SET address = :a
                    WHERE lastName = :ln
                """);

            query.setParameter("a", lastName);
            query.setParameter("ln", lastName);

            currEmployees = query.executeUpdate();
            em.getTransaction().commit();

        } catch (Exception ex) {
            em.getTransaction().rollback();

            System.out.printf("%d employees have been added to the database%n", currEmployees);

        } finally {
            em.close();
        }
    }
}
