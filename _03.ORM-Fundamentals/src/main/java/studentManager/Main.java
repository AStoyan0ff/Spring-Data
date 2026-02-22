package studentManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import studentManager.entities.Student;

public class Application {
    static void main() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("studentPU");
        EntityManager em = emf.createEntityManager();

        saveStudent(em, emf);
        readStudent(em, emf);
        updateStudent(em, emf);
        deleteStudent(em, emf);

    }

    private static void deleteStudent(EntityManager em, EntityManagerFactory emf) {

        try (em; emf) {

            em.getTransaction().begin();
            Student toDelete = em.find(Student.class, 7L);

            em.remove(toDelete);
            em.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void updateStudent(EntityManager em, EntityManagerFactory emf) {
        try (em; emf) {

            Student student = em.find(Student.class, 6L);
            em.getTransaction().begin();

            if (student != null) {

                student.setName("Koko");
                student.setAge(45);

                em.getTransaction().commit();

            } else {

                System.out.println("Student not found");
                em.getTransaction().rollback();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void readStudent(EntityManager em, EntityManagerFactory emf) {
        try (em; emf) {

            Student student = em.find(Student.class, 5);

            if (student != null) {
                System.out.println("Student found: " + student.getName() + ", Age: " + student.getAge());

            } else {

                System.out.println("Student not found");
            }

        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    private static void saveStudent(EntityManager em, EntityManagerFactory emf) {

        try {

        em.getTransaction().begin();
        Student student = new Student("Невена", 43);

            // добавя го в persistence context
        em.persist(student);

            // при commit на транзакцията ще се изпълни INSERT в базата
            // т.е: INSERT INTO students (name, age) VALUES ('Andrey', 41);
        em.getTransaction().commit();


            System.out.println("Student ID: " + student.getId());
            System.out.println("Student NAME: " + student.getName() + ", AGE: " + student.getAge());

        } catch (Exception e) {
            e.printStackTrace();

            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }

        } finally {
            em.close();
            emf.close();
        }
    }
}
