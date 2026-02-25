package DTOS;

public class AddressesDTO {
    private final String text;
    private final String townName;
    private final Integer employeesCount;

    public AddressesDTO(String text, String townName, Integer employeesCount) {
        this.text = text;
        this.townName = townName;
        this.employeesCount = employeesCount;
    }

    public String getText() {
        return text;
    }

    public String getTownName() {
        return townName;
    }

    public Integer getEmployeesCount() {
        return employeesCount;
    }
}
