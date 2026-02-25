package DTOS;

public class EmployeeProjectDTO {

    private final String firstName;
    private final String lastName;
    private final String jobTitle;
    private final String projectName;

    public EmployeeProjectDTO(String firstName, String lastName, String jobTitle, String projectName) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
        this.projectName = projectName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getJobTitle() {
        return jobTitle;
    }
    public String getProjectName() {
        return projectName;
    }
}
