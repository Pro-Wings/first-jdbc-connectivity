package com.prowings.jdbc;

import java.util.List;

public class TestStudentRepository {
	
	public static void main(String[] args) {
		
		StudentRepository repo = new StudentRepository();
		
		Student retrivedStd = repo.getStudentByRoll(30);
		System.out.println(retrivedStd);
		
		List<Student> stdList = repo.getAllStudents();
		System.out.println(">>>>>>std list >>>>>>>>"+stdList);
		
		
		Student std = new Student(50, "ZZZ", "Dubai");
		boolean res = repo.addNewStudent(std);
		System.out.println("Student added ::: "+res);
		
	}

}
