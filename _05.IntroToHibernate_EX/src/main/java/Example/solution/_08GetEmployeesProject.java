package Example.solution;

import DTOS.EmployeeProjectDTO;
import jakarta.persistence.*;

import java.util.List;
import java.util.Scanner;

public class _08GetEmployeesProject {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory mf = Persistence.createEntityManagerFactory("hibernate-exercise");
        EntityManager em = mf.createEntityManager();

        int eID = scanner.nextInt();

        printProjects(em, eID);

        em.close();
        scanner.close();
    }

    private static void printProjects(EntityManager em, int id) {

        TypedQuery<EmployeeProjectDTO> query = em.createQuery(
            """
                SELECT new DTOS.EmployeeProjectDTO(
                    e.firstName,
                    e.lastName,
                    e.jobTitle,
                    p.name)
                FROM Employee e
                    JOIN e.projects p
                WHERE e.id = :id
                ORDER BY p.name
            """,
            EmployeeProjectDTO.class
        );

        query.setParameter("id", id);
        List<EmployeeProjectDTO> resultList = query.getResultList();

        if (resultList.isEmpty()) {
            return;
        }

        EmployeeProjectDTO first = resultList.getFirst();

        System.out.printf("%s %s -> %s%n",
            first.getFirstName(),
            first.getLastName(),
            first.getJobTitle());

        for (EmployeeProjectDTO epd : resultList) {
            System.out.printf(" -> %s%n", epd.getProjectName());
        }

        em.close();
    }
}
