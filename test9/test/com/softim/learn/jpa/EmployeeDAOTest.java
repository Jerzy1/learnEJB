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
