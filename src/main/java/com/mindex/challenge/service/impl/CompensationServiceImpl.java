package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Validates a compensation and inserts it into the database.
     * @param compensation - Base compensation information.
     * @return Same compensation - honestly, there's only a validity check on the employeeId.
     */
    @Override
    public Compensation create(Compensation compensation) {
        String id = compensation.getEmployeeId();
        Employee employee = employeeRepository.findByEmployeeId(id);

        LOG.debug("Creating compensation for employee [{}]", id);

        if (employee == null) {
            throw new RuntimeException("Invalid Employee Id.");
        }

        // We'll retrieve this when reading, no need to store it if the user decides to put it in.
        compensation.setEmployee(null);

        compensationRepository.insert(compensation);

        return compensation;
    }

    /**
     * It's a GET endpoint - Compensation must already exist.
     * @param id - EmployeeId to find a compensation.
     * @return - Found compensations with updated Employee data.
     */
    @Override
    public List<Compensation> read(String id) {
        LOG.debug("Getting compensation with id [{}]", id);

        List<Compensation> compensations = compensationRepository.findByEmployeeId(id);

        for (Compensation compensation : compensations) {
            if (compensation == null) {
                throw new RuntimeException("Compensation not created for Employee with Id: " + id);
            }

            Employee employee = employeeRepository.findByEmployeeId(id);
            if (employee == null) {
                throw new RuntimeException("Invalid EmployeeId: " + id);
            }

            List<Employee> directReports = employee.getDirectReports();
            directReports = directReportUpdate(directReports);
            employee.setDirectReports(directReports);

            compensation.setEmployee(employee);
        }

        return compensations;
    }

    /**
     * I know this isn't required, but I feel like I should be thorough with what's given. If an employee's ID is
     * updated, their Compensation should too, right? Though I need to ask about Compensations, because I just
     * realized that I'm not sure if an employee can have multiple of them...
     */
    @Override
    public Compensation update(Compensation compensation) {
        LOG.debug("Updating employee id for compensation.");

        return compensationRepository.save(compensation);
    }

    /**
     * I know duplicate code is a sin... but I want to be thorough with information reading, and I don't see
     * the need to make this a public function or available to the rest of the package, unless there are more
     * types added that can read Employee.
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
