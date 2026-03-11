package SPRING;

import SPRING.entities.Department;
import SPRING.entities.Employee;
import SPRING.repositories.DepartmentRepository;
import SPRING.repositories.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public void run(String... args) throws Exception {

//        Query 1

        List<Employee> names = employeeRepository.findByLastName("Ivanov");
        System.out.println(names);

//        Query 2

        List<Employee> res = employeeRepository
          .findBySalaryBetweenOrderBySalaryDesc(
              BigDecimal.valueOf(3000),
              BigDecimal.valueOf(4000));

        System.out.println(res);

//        Query 3

        List<Employee> company = employeeRepository
            .findByEmailContainingAndDepartmentNameOrderByHireDateAsc("company", "Engineering");

        System.out.println(company);

        List<Employee> projectAlpha = employeeRepository.findByProjectsName("Project Alpha");
        System.out.println(projectAlpha);

//        Query 4

        int dept = departmentRepository.countEmployeesByDepartmentName("Marketing");
        System.out.println(dept);

//        Query 5

        int salary = employeeRepository.updateSalaryByDepartmentName("Engineering", 10);
        System.out.println(salary);

//       Query 6

        List<Employee> result = employeeRepository.findByNames("a", 1);
        System.out.println(result);
    }
}
