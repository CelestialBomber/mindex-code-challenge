package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public Employee read(String id) {
        LOG.debug("Creating employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        List<Employee> directReports = directReportUpdate(employee.getDirectReports());
        employee.setDirectReports(directReports);

        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }

    @Override
    public ReportingStructure report(String id) {
        LOG.debug("Retrieving report of id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        List<Employee> directReports = directReportUpdate(employee.getDirectReports());
        employee.setDirectReports(directReports);


        return new ReportingStructure(employee);
    }

    /**
     * Helper function to recursively update direct reports at time of viewing.
     * @param directReports - Direct report to look into and obtain information for.
     * @return - Detailed direct report for viewing.
     */
    private List<Employee> directReportUpdate(List<Employee> directReports)
    {
        if (directReports != null)
        {
            for (int reportIdx = 0; reportIdx < directReports.size(); reportIdx++)
            {
                Employee employeeInReport = directReports.get(reportIdx);
                employeeInReport = employeeRepository.findByEmployeeId(employeeInReport.getEmployeeId());
                employeeInReport.setDirectReports(directReportUpdate(employeeInReport.getDirectReports()));
                directReports.set(reportIdx, employeeInReport);
            }
        }
        return directReports;
    }
}
