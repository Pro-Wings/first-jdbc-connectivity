package com.prowings.jdbc;

import java.util.List;

public class TestStudentRepository {
	
	public static void main(String[] args) {
		
		StudentRepository repo = new StudentRepository();
		
//		//get student by roll number
//		Student retrivedStd = repo.getStudentByRoll(30);
//		System.out.println(retrivedStd);
//		
//		//get all students
//		List<Student> stdList = repo.getAllStudents();
//		System.out.println(">>>>>>std list >>>>>>>>"+stdList);
		
//		//insert new student
//		Student std = new Student(60, "RRR", "HR");
//		boolean res = repo.addNewStudent(std);
//		System.out.println("Student added ::: "+res);
		
//		//update existing student
//		Student updtStd = new Student(30, "Sham", "Mumbai");
//		Student updatedStudent = repo.updateStudent(20, updtStd);
//		System.out.println("updated student : "+ updatedStudent);
//
//		//delete student by roll number
//		repo.deleteStudentByRoll(50);
		
		
		repo.testCallableStmt();
		
	}

}
