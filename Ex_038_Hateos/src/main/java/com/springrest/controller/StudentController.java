package com.springrest.controller;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Link;


import com.springrest.model.Student;

@RestController
@RequestMapping("/api")
public class StudentController {
	
	@GetMapping("/students/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable Integer id){ 
		
		Student student = new Student(id, "Rohan","Bengalore", "rohan@gmail.com");
		
		
		//Link to this student
		Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(StudentController.class).getStudentById(id)).withSelfRel();
		
		// Link to all students
		Link allStudentsLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(StudentController.class).getAllStudents()).withRel("All-Students");
		
		// Link to delete
		Link deleteLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(StudentController.class).deleteStudent(id)).withRel("delete");
		
		student.add(selfLink);
		student.add(allStudentsLink);
		student.add(deleteLink);
		
		return new ResponseEntity<>(student, HttpStatus.OK);
		
	}
	
	//Get all students with HATEOAS links
	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudents(){
		List<Student> list = new ArrayList<>();
		
		Student s1 = new Student(1, "Rohan", "Bengaluru", "rohan@gmail.com");
        Student s2 = new Student(2, "Ravi", "Hyderabad", "ravi@gmail.com");
        Student s3 = new Student(3, "Raj", "Mumbai", "raj@gmail.com");
        
        // add self link to each student
        s1.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(StudentController.class).getStudentById(1)).withSelfRel());
        
        s2.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(StudentController.class).getStudentById(2)).withSelfRel());
        
        s3.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(StudentController.class).getStudentById(3)).withSelfRel());

		list.add(s1);
		list.add(s2);
		list.add(s3);

        return new ResponseEntity<>(list, HttpStatus.OK);
        
        
		
	}
	// POST — add student
    @PostMapping("/student")
    public ResponseEntity<Student> addStudent(
            @RequestBody Student student) {
        student.setId(1);

        // self link after creation
        Link selfLink = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder
                .methodOn(StudentController.class)
                .getStudentById(student.getId()))
            .withSelfRel();

        student.add(selfLink);

        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }
 // PUT — update student
    @PutMapping("/student")
    public ResponseEntity<Student> updateStudent(
            @RequestBody Student student) {

        Link selfLink = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder
                .methodOn(StudentController.class)
                .getStudentById(student.getId()))
            .withSelfRel();

        student.add(selfLink);

        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    // DELETE — delete student
    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> deleteStudent(
            @PathVariable Integer id) {
        return new ResponseEntity<>(
            "Student deleted with id " + id, HttpStatus.OK);
    }
    
}
