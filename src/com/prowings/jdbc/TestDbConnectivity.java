package com.prowings.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDbConnectivity {

	public static void main(String[] args) {

		Student std1 = new Student(10, "Ram", "Pune");

		// store std1 into DB - table

		// Steps to connect DB

		Connection con = null;

		try {
			// 1-Register the Driver class
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2-Create Connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdbconnectivity", "root", "prowingsroot");
			
			//3-Create Statement
			Statement stmt = con.createStatement();
			
			//4-Execute the Query
			ResultSet rs = stmt.executeQuery("select * from student");
			
			int rno;
			String nm;
			String addr;
			
			//print the ResultSet
            while (rs.next()) {
                rno = rs.getInt("roll");
                nm = rs.getString("name").trim();
                addr = rs.getString("address").trim();
                
                System.out.println("Roll : "+rno +"    Name : "+nm+ "    Address : "+addr);
            }
			
			
			

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}

}
