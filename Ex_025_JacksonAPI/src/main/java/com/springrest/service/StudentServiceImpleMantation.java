package com.springrest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrest.model.Student;
import com.springrest.repository.StudentRepository;

@Service
public class StudentServiceImpleMantation implements StudentService{

	@Autowired
	private StudentRepository repository;
	
	
	@Override
	public Student saveStudent(Student student) {
		
		return repository.save(student);
	}

	@Override
	public List<Student> getAllStudents() {
		
		return repository.findAll();
	}

	@Override
	public Student getStudentById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new RuntimeException("Student not found with id"+id));
	}

}
