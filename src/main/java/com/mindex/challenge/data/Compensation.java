package com.mindex.challenge.data;

public class Compensation
{
    private Employee employee;
    private int salary;
    private int effectiveDate;

    public Compensation(Employee e, int s, int ed)
    {
        this.employee = e;
        salary = s;
        effectiveDate = ed;
    }

    // Getters
    public Employee getEmployee() { return employee; }
    public int getSalary() { return salary; }
    public int getEffectiveDate() { return effectiveDate; }

    // Setters
    public void setEmployee(Employee e) { employee = e; }
    public void setSalary(int s) { salary = s; }
    public void setEffectiveDate(int ed) { effectiveDate = ed; }


}
