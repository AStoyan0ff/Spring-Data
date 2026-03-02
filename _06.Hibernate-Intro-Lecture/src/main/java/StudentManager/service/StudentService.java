package StudentManager.service;

import StudentManager.entity.Student;
import StudentManager.repository.StudentRepository;
import java.util.List;

public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService() {
         this.studentRepository = new StudentRepository();
    }

    public void addNewStudent(String name, int age) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }

        if (age < 0 || age > 200) {
            throw new IllegalArgumentException("Age must be between 0 and 200.");
        }

        Student student = new Student(name, age);
        studentRepository.save(student);
    }

    public Student findStudentById(int id) {

        if (id < 0 ) {
            throw new IllegalArgumentException("ID must be greater than 0.");
        }

        return studentRepository.findById(id);

    }

    public List<Student> getAllStudents() {
        return studentRepository.getAll();
    }

    public void updateStudentById(int idOfStudentToUpdate, String newName, int newAge) {

        if (idOfStudentToUpdate < 0 ) {
            throw new IllegalArgumentException("ID must be greater than 0.");
        }

        Student student = studentRepository.findById(idOfStudentToUpdate);

        if (student == null) {
            throw new IllegalArgumentException("Student with ID " + idOfStudentToUpdate + " not found.");
        }

        if (newName == null || newName.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }

        if (newAge < 0 || newAge > 200) {
            throw new IllegalArgumentException("Age must be between 0 and 200.");
        }

        try {
            studentRepository.updateById(idOfStudentToUpdate, newName, newAge);

        } catch (Exception e) {
            throw new RuntimeException("Failed to update student: " + e.getMessage(), e);
        }
    }

    public long getStudentsCount() {
        return studentRepository.getStudentCount();
    }
}
