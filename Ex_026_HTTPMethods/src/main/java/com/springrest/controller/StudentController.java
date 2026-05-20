package com.springrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.model.Student;
import com.springrest.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentController {
	
	@Autowired
	private StudentService service;
	
	// saving student
	@PostMapping("/student")
	public ResponseEntity<Student> addStudent(@RequestBody Student student){
		Student saved = service.saveStudent(student);
		return new ResponseEntity<Student> (saved, HttpStatus.CREATED);  // 201 created
	}
	
	// Get all students from db
	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudents(){
		List<Student> list = service.getAllStudents();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	//Get student by id 
	@GetMapping("/students/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable Integer id){
		Student student = service.getStudentById(id);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
	
	@PostMapping("/student/unmarshal")
	public ResponseEntity<String> unmarshal(@RequestBody Student student) {
	    System.out.println(student);
	    return new ResponseEntity<>("Data received", HttpStatus.OK);
	}
	
	// Full update
	@PutMapping("/update")
	public ResponseEntity<Student> fullUpdate(@RequestBody Student student){
		Student updateStudent = service.saveStudent(student);
		return new ResponseEntity<Student>(updateStudent, HttpStatus.OK);
	}
	
	// partial Update
	@PatchMapping("/partialUpdate/{id}/{city}")
	public ResponseEntity<Student> partialUpdate(@PathVariable Integer id, @PathVariable String city){
		
		Student updated = service.updateStudentCity(id, city);
		
		return new ResponseEntity<Student>(updated, HttpStatus.OK);
	}
	
	// DELETE — delete student by id
    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Integer id) {
        service.deleteStudent(id);
        return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK); // 200
    }
	
	
	
	
	
}
