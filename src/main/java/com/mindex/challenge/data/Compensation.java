package com.mindex.challenge.data;

public class Compensation
{
    private String employeeId;
    private Employee employee;
    private int salary;
    private String effectiveDate;

    public Compensation() { }

    // Getters
    public String getEmployeeId() { return employeeId; }
    public Employee getEmployee() { return employee; }
    public int getSalary() { return salary; }
    public String getEffectiveDate() { return effectiveDate; }

    // Setters
    public void setEmployeeId(String id) { employeeId = id; }
    public void setEmployee(Employee e) { employee = e; }
    public void setSalary(int s) { salary = s; }
    public void setEffectiveDate(String ed) { effectiveDate = ed; }


}
