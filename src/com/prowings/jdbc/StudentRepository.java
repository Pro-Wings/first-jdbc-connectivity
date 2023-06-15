package com.prowings.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

	public StudentRepository() {
		super();
	}

	public List<Student> getAllStudents() {

		List<Student> result = new ArrayList<>();
		Student s = new Student();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Connection con = MyConection.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from student");

			int rno;
			String nm;
			String addr;

			while (rs.next()) {
				rno = rs.getInt("roll");
				nm = rs.getString("name").trim();
				addr = rs.getString("address").trim();

				System.out.println("Roll : " + rno + "    Name : " + nm + "    Address : " + addr);

				s.setRoll(rno);
				s.setName(nm);
				s.setAddress(addr);
				result.add(s);
			}

		} catch (SQLException e) {
			System.out.println("error while retriving data");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			MyConection.closeConnection();
		}

		return result;
	}

	public Student getStudentByRoll(int roll) {
		Student s = new Student();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Connection con = MyConection.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from student where roll =" + roll);

			int rno;
			String nm;
			String addr;

			if (rs.next()) {
				rno = rs.getInt("roll");
				nm = rs.getString("name").trim();
				addr = rs.getString("address").trim();

				System.out.println("Roll : " + rno + "    Name : " + nm + "    Address : " + addr);

				s.setRoll(rno);
				s.setName(nm);
				s.setAddress(addr);
			} else {
				System.out.println("no student record found with " + roll + " number");
			}

		} catch (SQLException e) {
			System.out.println("error while retriving data");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			MyConection.closeConnection();
		}

		return s;

	}

	public boolean addNewStudent(Student std) {
		Statement stmt = null;
		boolean res = false;
		try {
			Connection con = MyConection.getConnection();
			stmt = con.createStatement();

			res = stmt.execute("insert into student values (" + std.getRoll() + ","+"\'"
					+ std.getName()+"\'" + ","+"\'" + std.getAddress() +"\'" +")");

		} catch (Exception e) {
			System.out.println("error while inserting the record");
			e.printStackTrace();
		}
		finally {
			
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MyConection.closeConnection();
		}

		return res;
	}

	public boolean deleteStudentByRoll(int roll) {
		return true;
	}

	public boolean updateStudent(int roll, Student std) {
		return true;
	}

}
