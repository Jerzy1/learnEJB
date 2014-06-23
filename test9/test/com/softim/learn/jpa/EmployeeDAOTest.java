/**
 * 
 */
package com.softim.learn.jpa;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Jerzy
 *
 */
public class EmployeeDAOTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		System.out.println("@After tearDown");
	}

	@Test	
	public void testAdd101Employee() {
		EmployeeJDBCOperations tet = new EmployeeJDBCOperations();
		tet.truncate();
		
		GenerateEmployees ge = new GenerateEmployees();
		ge.generateByJPA();
		
		assertEquals(100 ,tet.countEmployees());
		
		tet.create101Employee();
		
		Employee emp =  ge.get101Employee();
		assertEquals(101 ,tet.countEmployees());
		assertTrue(101 == emp.getId());
		assertTrue("John101".equals(emp.getName()));
		assertTrue("manager".equals(emp.getLevel()));
		assertTrue(123456 == emp.getSalary());
	}

}
