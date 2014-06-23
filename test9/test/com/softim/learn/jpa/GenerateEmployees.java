package com.softim.learn.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class GenerateEmployees {

	public void generateByJPA(){
		int i = 0;
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("test9test");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();

		Employee empl;

		while (i < 100) {
			empl = new Employee();
			empl.setName("Jon" + i);
			empl.setSalary(i * 10 + 10);
			em.persist(empl);
			i++;
		}
		et.commit();
		em.close();
	}
	
	public Employee get101Employee(){
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("test9test");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();

		Employee empl=em.find(Employee.class, 101);

		et.commit();
		em.close();
		
		return empl;
	}
	
	public static void main(String[] args) {
		GenerateEmployees ge = new GenerateEmployees();
		ge.generateByJPA();
	}
}
