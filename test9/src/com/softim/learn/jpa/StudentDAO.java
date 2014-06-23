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
	
	public StudentDAO() {
		super();
	}
	
	public StudentDAO(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}
	
	public Student findStudent(int id){
		Student student = entityManager.find(Student.class, id);
		return student;
	}
	
	public Student createStudent(String name, String enrollmentId, String tutor){
		Student student = new Student();
		student.setName(name);
		student.setEnrollmentID(enrollmentId);
		student.setTutorName(tutor);
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
