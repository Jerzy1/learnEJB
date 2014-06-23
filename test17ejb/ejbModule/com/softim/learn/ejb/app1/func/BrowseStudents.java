package com.softim.learn.ejb.app1.func;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
//import org.springframework.stereotype.Service;

import com.softim.learn.jpa.Student;

/**
 * Session Bean implementation class BrowseStudents
 */

@Stateful(mappedName = "BrowseStudents")
@LocalBean
public class BrowseStudents implements BrowseStudentsInterfaceLocal {
	private int currentPage;
	private int pageSize = 20;

	/**
	 * Default constructor.
	 */
//	public BrowseStudents() {
//		// TODO Auto-generated constructor stub
//	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<Student> getPage(int pgNum) {
		// TODO: how to get one page?
		return new ArrayList<Student>();
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void writeToConsole() {
		System.out.println("BrowseStudents object called");
	}
}
