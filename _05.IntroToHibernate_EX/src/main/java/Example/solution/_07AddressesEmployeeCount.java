package Example.solution;

import DTOS.AddressesDTO;
import jakarta.persistence.*;
import java.util.List;

public class _07AddressesEmployeeCount {
    static void main() {

        EntityManagerFactory mf = Persistence.createEntityManagerFactory("hibernate-exercise");
        EntityManager em = mf.createEntityManager();

        countAddressEmployees(em);
        em.close();
    }

    private static void countAddressEmployees(EntityManager em) {
        em.getTransaction().begin();

        try (em) {

            TypedQuery<AddressesDTO> query = em.createQuery(
                """
                        SELECT a.text, a.town.name, size(a.employees) AS employees
                        FROM Address a
                        ORDER BY SIZE(a.employees) DESC
                    """,
                AddressesDTO.class);
            query.setMaxResults(10);

            List<AddressesDTO> addressList = query.getResultList();

            for (AddressesDTO a : addressList) {

                String townName = a.getTownName();
                if (townName == null) townName = "n/a";

                System.out.printf("%s, %s -> %s%n", a.getText(), townName, a.getEmployeesCount());
            }

        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
    }
}
