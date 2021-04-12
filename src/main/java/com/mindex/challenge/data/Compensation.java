package com.mindex.challenge.data;

public class Compensation
{
    private String employeeId;
    private Employee employee;
    private int salary;
    private long effectiveDate;

    /**
     * I know it said to have the fields "employee," "salary," and "effectiveDate,"
     * but I figured direct access to employeeId would be preferable, while only storing
     * Employee after getting the Compensation object.
     */
    public Compensation() { }

    // Getters
    public String getEmployeeId() { return employeeId; }
    public Employee getEmployee() { return employee; }
    public int getSalary() { return salary; }
    public long getEffectiveDate() { return effectiveDate; }

    // Setters
    public void setEmployeeId(String id) { employeeId = id; }
    public void setEmployee(Employee e) { employee = e; }
    public void setSalary(int s) { salary = s; }
    public void setEffectiveDate(long ed) { effectiveDate = ed; }


}
