package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ExamplePreparedStatement {

	public static void main(String[] args) {
		final String dbUrl = "jdbc:mysql://localhost:3306/office";
		final String username = "root";
		final String password = "root";
		final String insertQuery = "INSERT INTO EMPLOYEE values (?, ?, ?, ?, ?)";
		final String selectQueryById = "SELECT * FROM EMPLOYEE WHERE EMP_ID = ?";
		
		Connection conn = null;
		PreparedStatement insertPreparedStatement;
		PreparedStatement selectPreparedStatement;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, username, password);
			insertPreparedStatement = conn.prepareStatement(insertQuery);
			
			insertPreparedStatement.setInt(1, 1004);
			insertPreparedStatement.setString(2, "Desouza");
			insertPreparedStatement.setString(3, "Lowrence");
			insertPreparedStatement.setString(4, "Steel city");
			insertPreparedStatement.setString(5, "Bokaro");
			
			insertPreparedStatement.executeUpdate();
			
			selectPreparedStatement = conn.prepareStatement(selectQueryById);
			selectPreparedStatement.setInt(1, 1004);
			
			ResultSet rs = selectPreparedStatement.executeQuery();
			

			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
