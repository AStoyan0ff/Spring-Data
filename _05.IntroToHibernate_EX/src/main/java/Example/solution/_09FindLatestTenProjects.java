package Example.solution;

import Example.entities.Project;
import jakarta.persistence.*;

import java.util.Comparator;
import java.util.List;

public class _09FindLatestTenProjects {
    static void main() {

        EntityManagerFactory mf = Persistence.createEntityManagerFactory("hibernate-exercise");
        EntityManager em = mf.createEntityManager();

        printLatestProjects(em);
        em.close();

    }

    private static void printLatestProjects(EntityManager em) {

        TypedQuery<Project> query = em.createQuery(
            """
                FROM Project p
                ORDER BY p.startDate DESC
            """,
            Project.class
        );

        List<Project> projectList = query
            .setMaxResults(10)
            .getResultList();

        projectList.sort(Comparator.comparing(Project::getName));

        for (Project p : projectList) {
            System.out.printf(

                "Project name: %s%n" +
                    " -> Project Description: %s%n" +
                    " -> Project Start Date:%s%n" +
                    " -> Project End Date: %s%n",

                p.getName(),
                p.getDescription(),
                p.getStartDate(),
                p.getEndDate()
            );
        }
    }
}
