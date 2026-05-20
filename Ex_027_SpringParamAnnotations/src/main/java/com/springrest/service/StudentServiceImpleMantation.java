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

	// full update
	@Override
	public Student updateStudent(Student student) {
		//fetch existing student
		Student existing = repository.findById(student.getId()).orElseThrow(() -> new RuntimeException("Student not found with id:"+student.getId()));
		
		//update all fields
		existing.setName(student.getName());
		existing.setCity(student.getCity());
		existing.setEmail(student.getEmail());
		
		//save and return
		return repository.save(existing);
	}

	
	// partial update
	@Override
	public Student updateStudentCity(Integer id, String city) {
		Student existingStudent = repository.findById(id).orElseThrow(() -> new RuntimeException("Student not found with id:"+id));
		
		// update only city
		existingStudent.setCity(city);
		return repository.save(existingStudent);
		
	}

	// Delete
	@Override
	public void deleteStudent(Integer id) {
		repository.findById(id).orElseThrow(() -> new RuntimeException("Student not found with id:"+id));
		repository.deleteById(id);
		
	}

}
