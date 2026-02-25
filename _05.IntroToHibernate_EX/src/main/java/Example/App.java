package Example;

import jakarta.persistence.*;

public class App {
    static void main() {

        EntityManagerFactory mf = Persistence.createEntityManagerFactory("hibernate-exercise");
        EntityManager em = mf.createEntityManager();

        // Hello @EveryOne ... :)

        em.close();
        mf.close();
    }
}
