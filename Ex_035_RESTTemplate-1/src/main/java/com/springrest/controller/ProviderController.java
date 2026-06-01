package com.springrest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.model.Student;

@RestController
@RequestMapping("/provider")
public class ProviderController {

    // GET — return single student
    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudent(
            @PathVariable Integer id) {
        Student student = new Student(
            id, "Rohan", "Bengaluru", "rohan@gmail.com");
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    // GET — return list of students
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> list = new ArrayList<>();
        list.add(new Student(1, "Rohan", "Bengaluru", "rohan@gmail.com"));
        list.add(new Student(2, "Ravi", "Hyderabad", "ravi@gmail.com"));
        list.add(new Student(3, "Raj", "Mumbai", "raj@gmail.com"));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // POST — receive student
    @PostMapping("/student")
    public ResponseEntity<String> addStudent(
            @RequestBody Student student) {
        System.out.println("Received: " + student.getName());
        return new ResponseEntity<>(
            "Student received: " + student.getName(),
            HttpStatus.CREATED);
    }
}