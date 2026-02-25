package Example.solution;

import Example.entities.Town;
import jakarta.persistence.*;
import java.util.List;

public class _02ChangeCasing {
    static void main() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-exercise");
        EntityManager em = emf.createEntityManager();

//      Вариант 1

        TypedQuery<Town> query = em.createQuery("SELECT t FROM Town AS t", Town.class);
        List<Town> towns = query.getResultList();

        em.getTransaction().begin();

        try {
            for (Town town : towns) {
                if (town.getName().length() > 5) {
                    em.persist(town);

                } else {
                    town.setName(town.getName().toUpperCase());
                }
            }
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
            emf.close();
        }

//      Вариант 2

        em.getTransaction().begin();

        try {
            Query selectQuery = em.createQuery("UPDATE Town AS t  SET t.name = lower(t.name) WHERE length(t.name) <= 5");
            int result = selectQuery.executeUpdate();
            em.getTransaction().commit();

            System.out.printf("Result of selectQuery: %d%n", result);

        } catch (Exception e) {
            em.getTransaction().rollback();

        }  finally {
            em.close();
            emf.close();
        }
    }
}
