package com.mindex.challenge;

import com.mindex.challenge.controller.EmployeeController;
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
	 * I don't particularly feel like recreating tons of Employee objects, so I'll do the same Autowiring that the
	 * DataBootstrapTest does to set up the rest of the tests.
	 */
	@Autowired
	private EmployeeController employeeController;

	@Test
	public void contextLoads() {
	}

	@Test
	public void report_test() {
		ReportingStructure rs = employeeController.report("16a596ae-edd3-4847-99fe-c4518e82c86f");
		assertEquals(employeeController.read("16a596ae-edd3-4847-99fe-c4518e82c86f"), rs.getEmployee());
		assertEquals(4, rs.getNumberOfReports());

	}
}
