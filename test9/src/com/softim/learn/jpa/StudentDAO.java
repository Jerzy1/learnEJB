package com.softim.learn.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Named
@ApplicationScoped
public class StudentDAO {

	@PersistenceContext
	EntityManager entityManager;
	
	
	public Student findStudent(int id){
		Student student = entityManager.find(Student.class, id);
		return student;
	}
	
	public Student save(Student student){
		entityManager.persist(student);
		return student;
	}
	
	public int getStudentCount(){
		Query q = entityManager.createQuery("select count(*) from Students");
		Integer cnt = (Integer) q.getSingleResult();
		return cnt.intValue();
	}
	
	public boolean hasEntityManager(){
		return entityManager!=null;
	}
	
}
