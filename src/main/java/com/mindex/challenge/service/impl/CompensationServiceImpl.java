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

import java.util.UUID;

@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * You need an Employee in order to create a Compensation, there's no making up a random UUID for this one.
     * SIDE-EFFECT : Compensation is added to the repository.
     * @param employee - Employee to create a base Compensation out of.
     * @return Newly created Compensation.
     */
    @Override
    public Compensation create(Employee employee) {
        LOG.debug("Creating compensation [{}]", employee);

        if (employee == null) {
            throw new RuntimeException("Invalid Employee.");
        }
        String employeeId = employee.getEmployeeId();
        Compensation compensation = new Compensation();
        compensation.setEmployeeId(employeeId);
        compensation.setEmployee(employeeRepository.findByEmployeeId(employeeId));
        compensationRepository.insert(compensation);

        return compensation;
    }

    /**
     * It's a GET endpoint - Compensation must already exist.
     * @param id - EmployeeId to find a compensation.
     * @return - Found compensation with updated Employee data.
     */
    @Override
    public Compensation read(String id) {
        LOG.debug("Getting compensation with id [{}]", id);

        Compensation compensation = compensationRepository.findByEmployeeId(id);

        if (compensation == null) {
            throw new RuntimeException("Compensation not created for Employee with Id: " + id);
        }

        Employee employee = employeeRepository.findByEmployeeId(id);
        compensation.setEmployee(employee);

        if (employee == null) {
            throw new RuntimeException("Invalid EmployeeId: " + id);
        }

        return compensation;
    }
}
