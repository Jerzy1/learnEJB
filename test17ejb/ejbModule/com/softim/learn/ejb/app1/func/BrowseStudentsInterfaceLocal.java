package com.softim.learn.ejb.app1.func;

import java.util.List;

import com.softim.learn.jpa.Student;

public interface BrowseStudentsInterfaceLocal {
	public int getCurrentPage();

	public List<Student> getPage(int pgNum);

	public int getPageSize();

	public void setPageSize(int pageSize);
}
