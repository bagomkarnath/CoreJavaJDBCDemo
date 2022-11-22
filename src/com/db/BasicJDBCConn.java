package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BasicJDBCConn {

	public static void main(String[] args) {
		final String dbUrl = "jdbc:mysql://localhost:3306/office";
		final String username = "root";
		final String password = "root";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(dbUrl, username, password);
			
			//Statement stmt = conn.createStatement();
			
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM EMPLOYEE");

			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
			}
			
			System.out.println("----------------------------");
			rs.beforeFirst();
			while (rs.next()) {
				System.out.println(rs.getInt("EMP_ID") + " " + rs.getString("FIRST_NAME") + " " + rs.getString("LAST_NAME"));
			}

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
