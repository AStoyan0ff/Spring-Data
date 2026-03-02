package StudentManager.repository;

import StudentManager.entity.Student;
import StudentManager.util.JpaUtil;
import jakarta.persistence.*;
import java.util.List;

public class StudentRepository implements AutoCloseable {
    private final EntityManager em;

    public StudentRepository() {
        this.em = JpaUtil.getEntityManager();
    }

    public void save(Student student) {

        try {

            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
    }

    public Student findById(int id) {

        try{
            return em.find(Student.class, id);

        } catch (Exception e) {

            System.out.println(e.getMessage());;
            return null;
        }
    }

    public List<Student> getAll() {

        try {

            return em.createQuery("SELECT s FROM Student s", Student.class)
                    .getResultList();

        } catch (Exception e) {

            System.out.println(e.getMessage());;
            return null;
        }
    }

    public long getStudentCount() {

        try {
            return em.createQuery("SELECT COUNT(s) FROM Student s", Long.class)
                    .getSingleResult();

        } catch (Exception e) {

            System.out.println(e.getMessage());;
            return 0;
        }
    }

    @Override
    public void close() throws Exception {
        em.close();
    }


    public void updateById(int idOfStudentToUpdate, String newName, int newAge) {

        try {
            em.getTransaction().begin();

            Student student = em.find(Student.class, idOfStudentToUpdate);
            student.setName(newName);
            student.setAge(newAge);

            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
    }
}
