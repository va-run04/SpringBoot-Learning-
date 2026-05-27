package com.springrest.controller;

import org.springframework.http.MediaType;
import java.security.PublicKey;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.model.Student;

@RestController
@RequestMapping("/api")
public class StudentController {
	
	
	@GetMapping(value = "/student",
			    produces = {
			    		MediaType.APPLICATION_JSON_VALUE,
			    		MediaType.APPLICATION_XML_VALUE
			    		}      
			)
	public ResponseEntity<Student> getStudent(){
		Student student = new Student(1, "Rohan", "Bengaluru", "rohan@gmail.com");
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/student",
			
			    consumes = {
		            MediaType.APPLICATION_JSON_VALUE,
		            MediaType.APPLICATION_XML_VALUE
		        },
			     produces = {MediaType.APPLICATION_JSON_VALUE,
			    		     MediaType.APPLICATION_XML_VALUE
			     }
			
			
			)
	public ResponseEntity<Student> addStudent(@RequestBody Student student){
		System.out.println(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
	}
	

}
