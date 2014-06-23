package com.softim.learn.ejb.app1.func;

import javax.ejb.Local;

import com.softim.learn.jpa.Student;

@Local
public interface StudentsManagementInterfaceLocal {
	public Student createNewStudent(Student student);
	public Student getStudentByID(int studentId);
	public Student saveStudent(Student student);
	public boolean hasStudenDAO();
}
