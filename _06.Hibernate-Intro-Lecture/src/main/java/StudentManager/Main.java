package StudentManager;

import StudentManager.entity.Student;
import StudentManager.ui.ConsoleOptions;
import jakarta.persistence.*;

public class Main {
    public static void main(String[] args) {

        ConsoleOptions consoleMenu = new ConsoleOptions();
        consoleMenu.startMenu();

    }

    private static void deleted(EntityManager em, EntityManagerFactory emf) {

        try {

            em.getTransaction().begin();
            Student toDelete = em.find(Student.class, 1L);
            em.remove(toDelete);
            em.getTransaction().commit();
            System.out.println("Deleted student: " + toDelete.getName());

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            em.close();
            emf.close();
        }
    }
}