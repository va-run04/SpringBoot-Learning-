package com.springrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.model.Student;
import com.springrest.service.StudentService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")  // allow React app on port 3000
public class StudentController {

    @Autowired
    private StudentService service;

    // POST — create student
    @PostMapping("/student")
    public ResponseEntity<Student> addStudent(
            @RequestBody Student student) {
        Student saved = service.saveStudent(student);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // GET — get all students
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> list = service.getAllStudents();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // GET — get student by id
    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(
            @PathVariable Integer id) {
        Student student = service.getStudentById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}