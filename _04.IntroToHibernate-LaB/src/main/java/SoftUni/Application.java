package SoftUni;

import SoftUni.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {
    static void main() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-demo");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        // Inheritance
        em.persist(new Car(5));
        em.persist(new Bike(10));

        // One-to-One
        BookDetail detail = new BookDetail("Hibernate basics book");
        Book book = new Book("Hibernate 101", detail);

        em.persist(book);

        // One-to-Many
        Author author = new Author("John Doe");
        author.addBook(new BookEntity("JPA Guide"));
        author.addBook(new BookEntity("Hibernate Deep Dive"));

        em.persist(author);

        // Many-to-Many
        Category java = new Category("Java");
        Category db = new Category("Databases");

        Product product = new Product("Hibernate Course");
        product.addCategory(java);
        product.addCategory(db);

        em.persist(java);
        em.persist(db);
        em.persist(product);

        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
