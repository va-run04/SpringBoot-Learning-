package com.springrest.service;

import java.util.List;

import com.springrest.model.Student;


public interface StudentService {
	
	public Student saveStudent(Student student);
	
	public List<Student> getAllStudents();
	
	public Student getStudentById(Integer id);
	
	
}
