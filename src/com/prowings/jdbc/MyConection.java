package com.prowings.jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyConection {
	
	static Connection con = null;

	public static Connection getConnection()
	{

		System.out.println("creating connection object!!");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Properties myProp = new Properties();
			myProp.load(
					new FileInputStream("C:\\Users\\Shree\\web-app-workspace\\first-jdbc-connectivity\\db.properties"));
			String url = myProp.getProperty("dburl");
			String usr = myProp.getProperty("dbusername");
			String pwd = myProp.getProperty("dbpwd");

			con = DriverManager.getConnection(url, usr, pwd);
			System.out.println("connection object created successfully!!");

		} catch (Exception e) {
			System.out.println("error in creating connection object!!");
			e.printStackTrace();
		}

		return con;
	}

	
	public static void closeConnection() {
		try {
			System.out.println("closing the connection");
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
