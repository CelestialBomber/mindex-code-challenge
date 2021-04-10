package com.mindex.challenge.data;

import java.util.List;

public class Employee {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String position;
    private String department;
    private List<Employee> directReports;

    public Employee() {
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Employee> getDirectReports() {
        return directReports;
    }

    public void setDirectReports(List<Employee> directReports) {
        this.directReports = directReports;
    }

    /**
     * Ahhh, testing when different objects are retrieved... what a joy.
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Employee)
        {
            Employee employee = (Employee) obj;
            boolean idCheck = this.employeeId.equals(employee.getEmployeeId());
            boolean firstNameCheck = this.firstName.equals(employee.getFirstName());
            boolean lastNameCheck = this.lastName.equals(employee.getLastName());
            boolean departmentCheck = this.department.equals(employee.getDepartment());
            boolean positionCheck = this.position.equals(employee.getPosition());
            // Too much of a hassle to ALSO check this, considering what it relies on
            // boolean reportCheck = this.directReports.equals(employee.getDirectReports());
            return idCheck && firstNameCheck && lastNameCheck && departmentCheck && positionCheck;
        }
        else
        {
            return false;
        }
    }
}
