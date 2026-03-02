package StudentManager.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    public Student() {}

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private String name;

    private int age;

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Student: " + name + ", Age: " + age;
    }
}
