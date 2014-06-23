package com.softim.learn.ejb.arquillian;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Generate10kStudnets {
	private Connection con = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	public void truncate() {

		try {
			prepareConnection();

			stmt = con.createStatement();
			stmt.execute("truncate table student");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	public int countStudents() {
		int count = -1;
		try {
			prepareConnection();

			stmt = con.createStatement();

			rs = stmt.executeQuery("SELECT count(*) FROM student");
			rs.next();
			count = rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return count;
	}

	public void create10kStudents() {
		try {
			prepareConnection();
			int i = 0;
			String s;
			while (i < 1000000) {
				stmt = con.createStatement();
				s="INSERT into student(enrollmentID, NAME, TUTORNAME) values(" + (i % 4 == 0 ? "'manager'" : "'worker'") + ",'John"
						+ i + "','tutor')";
				if(i % 1000 == 0) System.out.println(s);
				stmt.execute(s);
				stmt.close();
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	private void prepareConnection() throws NamingException, SQLException {
		Context initialContext = new InitialContext();
		DataSource datasource = (DataSource) initialContext.lookup("java:/env/ejbLearnDB");
		con = datasource.getConnection();
	}

	private void closeConnection() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException sqlEx) {
			}
			rs = null;
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException sqlEx) {
			}
			stmt = null;
		}
	}

}
