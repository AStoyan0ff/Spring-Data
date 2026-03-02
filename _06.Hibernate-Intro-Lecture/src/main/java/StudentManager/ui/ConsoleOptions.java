package StudentManager.ui;

import StudentManager.entity.Student;
import StudentManager.service.StudentService;
import java.util.Scanner;

public class ConsoleOptions {

    private final Scanner scanner;
    private final StudentService studentService;

    public ConsoleOptions() {
        this.scanner = new Scanner(System.in);
        this.studentService = new StudentService();
    }

    public void startMenu() {
        showMenu();

        int choice = -1;
        while (choice != 0) {
            choice = getAnInt("Enter your choice: ");

            if (choice == 1) {
                addStudent();

            } else if (choice == 2) {
                findStudentById();

            } else if (choice == 3) {
                listAllStudents();

            } else if (choice == 4) {
                updateStudent();

            } else if (choice == 6) {
                countAllStudents();

            } else {
                System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private void countAllStudents() {

        long totalStudents = studentService.getStudentsCount();
        System.out.println("Total number of students: " + totalStudents);
    }

    private void updateStudent() {

        int idOfStudentToUpdate = getAnInt("Enter the ID of the student to update: ");
        String newName = getAString("Enter the new name: ");
        int newAge = getAnInt("Enter the new age: ");

        try {
            studentService.updateStudentById(idOfStudentToUpdate, newName, newAge);

        } catch (Exception e) {
            System.out.println("An error occurred while updating the student: " + e.getMessage());
        }
    }

    private void listAllStudents() {
        System.out.println("All Students:");

        try {
            studentService.getAllStudents().forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("An error occurred while fetching students: " + e.getMessage());
        }
    }

    private void findStudentById() {
        int id = getAnInt("Enter Student ID: ");

        Student student = studentService.findStudentById(id);

        if  (student != null) {
            System.out.println("Student found: " + student);

        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    private void addStudent() {
        String name = getAString("Enter student name: ");
        int age = getAnInt("Enter student age: ");

        try {
            studentService.addNewStudent(name, age);

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showMenu() {

        System.out.println("\n=== Student Management System ===");
        System.out.println("1. Add a new student");
        System.out.println("2. Find student by ID");
        System.out.println("3. List all students");
        System.out.println("4. Update a student");
        System.out.println("5. Delete a student");
        System.out.println("6. Count all students");
        System.out.println("6. Search students by name pattern");
        System.out.println("0. Exit");
        System.out.println("=================================");
    }

    private int getAnInt(String prompt) {
        System.out.println(prompt);

        int value = Integer.parseInt(scanner.nextLine().trim());
        return value;
    }

    private String getAString(String prompt) {

        System.out.println(prompt);
        return scanner.nextLine().trim();
    }
}
