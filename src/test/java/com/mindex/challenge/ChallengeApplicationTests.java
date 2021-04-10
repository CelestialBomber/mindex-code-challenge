package com.mindex.challenge;

import com.mindex.challenge.controller.CompensationController;
import com.mindex.challenge.controller.EmployeeController;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.ReportingStructure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChallengeApplicationTests {

	/**
	 * I don't particularly feel like creating tons of mock objects here, so I'll use what already exists.
	 */
	@Autowired
	private EmployeeController employeeController;

	@Autowired
	private CompensationController compensationController;

	@Test
	public void contextLoads() {
	}

	@Test
	public void report_test() {
		ReportingStructure rs = employeeController.report("16a596ae-edd3-4847-99fe-c4518e82c86f");
		assert(employeeController.read("16a596ae-edd3-4847-99fe-c4518e82c86f").equals(rs.getEmployee()));
		assertEquals(4, rs.getNumberOfReports());
	}

	@Test
	public void compensation_test() {
		Compensation compensation = compensationController.read("16a596ae-edd3-4847-99fe-c4518e82c86f");
		assert(employeeController.read("16a596ae-edd3-4847-99fe-c4518e82c86f").equals(compensation.getEmployee()));
		assertEquals(83000, compensation.getSalary());
		assertEquals(1619064000000L, compensation.getEffectiveDate());
	}
}
