package com.softim.learn.ejb.app1.func;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
//import org.springframework.stereotype.Service;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.softim.learn.jpa.Student;

/**
 * Session Bean implementation class BrowseStudents
 */

@Stateful(mappedName = "BrowseStudents")
@LocalBean
public class BrowseStudents implements BrowseStudentsInterfaceLocal {
	private int currentPage;
	private boolean currentPageEmpty=true;
	private int pageSize = 20;

	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
//	/**
//	 * Default constructor.
//	 */
//	public BrowseStudents() {
//		// TODO Auto-generated constructor stub
//	}

	public void initBrowsing() throws NamingException, SQLException{
		Context initialContext = new InitialContext();
		DataSource datasource = (DataSource)initialContext.lookup("java:/env/ejbLearnDB");
	      con = datasource.getConnection();
	      stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
	      rs = stmt.executeQuery("select ID, enrollmentID, NAME, TUTORNAME from student");
	      rs.setFetchSize(5*pageSize);
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public List<Student> getPage(int pgNum) throws SQLException {
		currentPage = pgNum;
		Student stud;
		boolean located = rs.absolute(currentPage * pageSize);
		currentPageEmpty=!located;
		ArrayList<Student> sl = new ArrayList<Student>();
		for (int i=0; i < pageSize; i++){
			if (located){
				stud = new Student();
				stud.setId(rs.getInt(1));
				stud.setEnrollmentID(rs.getString(2));
				stud.setName(rs.getString(3));
				stud.setTutorName(rs.getString(4));
				sl.add(stud);
				if (!rs.next()){
					break;
				}
			}else{
				break;
			}
		}
		return sl;
	}

	public Student getStudentAtPos(int pos) throws SQLException{
		boolean located = rs.absolute(pos);
		if (located){
			Student stud = new Student();
			stud.setId(rs.getInt(1));
			stud.setEnrollmentID(rs.getString(2));
			stud.setName(rs.getString(3));
			stud.setTutorName(rs.getString(4));
			return stud;
		}
		return null;
	}
	
	public Student getNextStudent() throws SQLException{
		boolean located = rs.next();
		if (located){
			Student stud = new Student();
			stud.setId(rs.getInt(1));
			stud.setEnrollmentID(rs.getString(2));
			stud.setName(rs.getString(3));
			stud.setTutorName(rs.getString(4));
			return stud;
		}
		return null;
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

	public boolean isCurrentPageEmpty() {
		return currentPageEmpty;
	}
	
}
