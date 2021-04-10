package com.mindex.challenge.data;


import java.util.Map;

public class ReportingStructure {
    private Employee employee;
    private int numberOfReports;

    public ReportingStructure(Employee e)
    {
        employee = e;
        numberOfReports = getReportCount(0, e);
    }

    // Getters
    public Employee getEmployee() { return this.employee; }
    public int getNumberOfReports() { return this.numberOfReports; }

    /**
     * Recursively obtains the number of reports under a supplied employee.
     * @param numberOfReports - The number of reports found at the time of recursion. Starts with a default value of 0.
     * @param employee - The employee to find the number of reports under.
     * @return The number of reports under a given employee.
     */
    private int getReportCount(int numberOfReports, Employee employee)
    {
        // I'm going to make this code active under the assumption that you cannot have an employee reporting under
        // and above another employee simultaneously. But, just in case I misinterpreted and this ISN'T the case,
        // I'll create another version of the function that checks for this.
        // EX:
        //          Kate                ...Dean
        //          /  \                   /  \    <-- assuming this two-way is not possible
        //       Pete   Dean...       Grace    Kate

        if (employee.getDirectReports() == null)
        {
            return 1;
        }
        for (Employee e : employee.getDirectReports())
        {
            String id = e.getEmployeeId();
            numberOfReports += getReportCount(numberOfReports, e);
        }
        return numberOfReports;
    }

    /**
     * - CURRENTLY UNUSED - Overloaded Function -
     * Recursively obtains the number of reports under a supplied employee, excluding duplicate entries.
     * @param numberOfReports - The number of reports found at the time of recursion. Starts with a default value of 0.
     * @param employee - The employee to find the number of reports under.
     * @param dupeCheck - A map that lists all employees that have been found. INITIALIZE AS AN EMPTY MAP. Or,
     *                  theoretically fix the function so it's overloaded, and the initial pass-in without the map
     *                  will run this function. Only if duplicate entries is a problem in the first place.
     * @return The number of reports under a given employee.
     */
    private int getReportCount(int numberOfReports, Employee employee, Map<String, Integer> dupeCheck)
    {
        //
        //          Kate                ...Dean
        //          /  \                   /  \    <-- assuming this two-way IS possible
        //       Pete   Dean...       Grace    Kate
        if (employee.getDirectReports() == null)
        {
            return 1;
        }
        for (Employee e : employee.getDirectReports())
        {
            String id = e.getEmployeeId();
            if (!dupeCheck.containsKey(id)){
                numberOfReports += getReportCount(numberOfReports, e);
                dupeCheck.put(id, 1);
            }
            else {
                // Currently, there's not really a purpose for adding to this number, but theoretically, if you wanted
                // to check for how many times an employee is found in duplicate chains, you could use this.
                dupeCheck.replace(id, dupeCheck.get(id) + 1);
            }
        }
        return numberOfReports;
    }
}
