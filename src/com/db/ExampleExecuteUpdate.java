package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExampleExecuteUpdate {

	public static void main(String[] args) {
		final String dbUrl = "jdbc:mysql://localhost:3306/office";
		final String username = "root";
		final String password = "root";
		
		Connection conn = null;
		Statement stmt;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, username, password);
			stmt = conn.createStatement();
			
			conn.setAutoCommit(false);
			
			stmt.executeUpdate("INSERT INTO EMPLOYEE values (103, 'Dev', 'Kapil', 'Highway 99', 'Chandigarh')");
			stmt.executeUpdate("INSERT INTO EMPLOYEE values (104, 'Dev-DEL', 'Kapil-DEL', 'Highway 99', 'Chandigarh')");
			
			int updateCount = stmt.executeUpdate("UPDATE EMPLOYEE SET LAST_NAME = 'McColin' WHERE EMP_ID = 102");
			
			System.out.println(updateCount + " ROW UPDATED ...");
			
			updateCount = stmt.executeUpdate("DELETE FROM EMPLOYEE WHERE EMP_ID = 104");
			
			System.out.println(updateCount + " ROW DELETED ...");
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM EMPLOYEE");

			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
			}

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

}
