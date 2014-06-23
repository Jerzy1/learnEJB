package com.softim.learn.ejb.app1.func;

import java.sql.SQLException;
import java.util.List;

import com.softim.learn.jpa.Student;

public interface BrowseStudentsInterfaceLocal {
	public int getCurrentPage();

	public List<Student> getPage(int pgNum) throws SQLException;

	public int getPageSize();

	public void setPageSize(int pageSize);
}
