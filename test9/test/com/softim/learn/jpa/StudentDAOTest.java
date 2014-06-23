/**
 * 
 */
package com.softim.learn.jpa;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Jerzy
 *
 */
public class StudentDAOTest {


	@Test
	public void testStudents() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("test9test");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		
		
		StudentDAO sdao = new StudentDAO(em);
		Student stud = sdao.createStudent("Edward", "A01002", "brak");
		
		et.commit();
		em.close();
		
	}

}
