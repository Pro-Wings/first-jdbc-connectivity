package com.prowings.jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class TestDbConnectivity {

	public static void main(String[] args) {

		Student std1 = new Student(10, "Ram", "Pune");

		// store std1 into DB - table

		// 5 Steps to connect DB

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 1-Register the Driver class
			Class.forName("com.mysql.cj.jdbc.Driver");

			Properties myProp = new Properties();

			myProp.load(
					new FileInputStream("C:\\Users\\Shree\\web-app-workspace\\first-jdbc-connectivity\\db.properties"));

			String url = myProp.getProperty("dburl");
			String usr = myProp.getProperty("dbusername");
			String pwd = myProp.getProperty("dbpwd");

			// 2-Create Connection
			con = DriverManager.getConnection(url, usr, pwd);

			// 3-Create Statement
			stmt = con.createStatement();

			// 4-Execute the Query
			rs = stmt.executeQuery("select * from student");

			int rno;
			String nm;
			String addr;

			// print the ResultSet
			while (rs.next()) {
				rno = rs.getInt("roll");
				nm = rs.getString("name").trim();
				addr = rs.getString("address").trim();

				System.out.println("Roll : " + rno + "    Name : " + nm + "    Address : " + addr);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				rs.close();
				stmt.close();
				// 5-Close the connection
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
