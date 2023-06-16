package com.prowings.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
//		Statement stmt = null;
		PreparedStatement stmt = null;
		int res = 0;
		try {
			Connection con = MyConection.getConnection();
//			stmt = con.createStatement();
			stmt = con.prepareStatement("insert into student values(?,?,?)");
					
			stmt.setString(2, std.getName());
			stmt.setInt(1, std.getRoll());
			stmt.setString(3, std.getAddress());

//			res = stmt.execute("insert into student values (" + std.getRoll() + ","+"\'"
//					+ std.getName()+"\'" + ","+"\'" + std.getAddress() +"\'" +")");

			res = stmt.executeUpdate();
			if(res==1)
				System.out.println("Student added successfully!!");
			
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

		return res==1 ? true : false;
	}

	public boolean deleteStudentByRoll(int roll) {
		String deleteQuery = "delete from student where roll =" + roll;
		Statement stmt = null;

		try {
			Connection con = MyConection.getConnection();
			stmt = con.createStatement();

			int deleteRes = stmt.executeUpdate(deleteQuery);
			
			if(deleteRes == 1)
			{
				System.out.println("Student deleted successfully!!!");
				return true;
			}
			else
				System.out.println("Student not deleted!!!");

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
		return false;
		
	}

	public Student updateStudent(int roll, Student std) {
		
		String updateQuery = "update student set name = "+"\'" +std.getName()+"\'" +"," +"address ="+"\'" +std.getAddress() +"\'" +" where roll ="+ roll;
		
		System.out.println("UPDATE QUERY : "+updateQuery);
		
		Statement stmt = null;
		boolean res = false;
		try {
			Connection con = MyConection.getConnection();
			stmt = con.createStatement();

			int updateRes = stmt.executeUpdate(updateQuery);
			
			if(updateRes == 1)
				System.out.println("Student updated successfully!!!");
			else
				System.out.println("Student not updated!!!");

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
		return null;
		
		
	}

	
	public void testCallableStmt()
	{
		CallableStatement stmt = null;

		try {
			Connection con = MyConection.getConnection();
			
			stmt = con.prepareCall("{call getAllStudentsWithGivenAddr(?)}");
				
			stmt.setString(1, "Pune");
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				int rno = rs.getInt("roll");
				String nm = rs.getString("name").trim();
				String addr = rs.getString("address").trim();

				System.out.println("Roll : " + rno + "    Name : " + nm + "    Address : " + addr);
			}

			
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

		
		
	}
	
}
