package com.softim.learn.ejb.app1.func;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.softim.learn.jpa.Student;
import com.softim.learn.jpa.StudentDAO;

@Stateless
public class StudentsManagement implements StudentsManagementInterfaceLocal {

	@Inject
	StudentDAO studentDAO;

	@Override
	public Student createNewStudent(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student getStudentByID(int studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student saveStudent(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasStudenDAO() {
		return true; //studentDAO != null;
	}

}
