package com.softim.learn.jpa;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class EmployeeJDBCOperations {
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;

	private void prepareConnection() throws SQLException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Properties properties = new Properties();

		InputStream resourceAsStream = EmployeeJDBCOperations.class.getClassLoader().getResourceAsStream("TestDatabase.properties");
		properties.load(resourceAsStream);
		Class.forName(properties.getProperty("JDBCclassname")).newInstance();
		// com.mysql.jdbc.Driver
		con = DriverManager.getConnection(properties.getProperty("JDBCurl"), properties.getProperty("JDBCuser"), properties.getProperty("JDBCpassword"));
		// con =
		// DriverManager.getConnection("jdbc:mysql://localhost/mydatabase?user=u1&password=password");
		// jdbc:mysql://localhost:3306/?user=root
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

	public void truncate() {

		try {
			prepareConnection();

			stmt = con.createStatement();
			stmt.execute("truncate table employee");

			// rs = stmt.executeQuery("SELECT * FROM world.country");
			// String c1;
			// while (rs.next()) {
			// c1 = rs.getString(1);
			// System.out.println(c1);
			// }

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	public int countEmployees() {
		int count = -1;
		try {
			prepareConnection();

			stmt = con.createStatement();

			rs = stmt.executeQuery("SELECT count(*) FROM employee");
			rs.next();
			count = rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return count;
	}

	public void create101Employee() {
		try {
			prepareConnection();

			stmt = con.createStatement();
			stmt.execute("INSERT into employee(ID,LEVEL,NAME,SALARY) values(101,'manager','John101',123456)");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	public static void main(String[] args) {
		EmployeeJDBCOperations tet = new EmployeeJDBCOperations();

		tet.truncate();
		
		tet.create101Employee();
	}
}
