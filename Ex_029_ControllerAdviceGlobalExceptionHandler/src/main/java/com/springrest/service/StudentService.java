package com.springrest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springrest.model.Student;

public interface StudentService {
	public Student saveStudent(Student student);
	public List<Student> getAllStudents();
	public Student getStudentById(Integer id);
	public Student updateStudent(Student student);
	public Student updateStudentCity(Integer id, String city);
	public void deleteStudent(Integer id);
}
