package com.springrest.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.springrest.model.Student;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(
    name = "Student Management API",
    description = "APIs for creating reading updating and deleting students"
)
public class StudentController {

    @GetMapping("/students")
    @Operation(
        summary = "Get all students",
        description = "Returns list of all students"
    )
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> list = new ArrayList<>();
        list.add(new Student(1, "Rohan", "Bengaluru", "rohan@gmail.com"));
        list.add(new Student(2, "Ravi", "Hyderabad", "ravi@gmail.com"));
        list.add(new Student(3, "Raj", "Mumbai", "raj@gmail.com"));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/students/{id}")
    @Operation(
        summary = "Get student by ID",
        description = "Pass student ID in URL to get single student"
    )
    public ResponseEntity<Student> getStudentById(
            @PathVariable Integer id) {
        Student student = new Student(
            id, "Rohan", "Bengaluru", "rohan@gmail.com");
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping("/student")
    @Operation(
        summary = "Add new student",
        description = "Pass student data in request body to create new student"
    )
    public ResponseEntity<Student> addStudent(
            @RequestBody Student student) {
        student.setId(1);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping("/student")
    @Operation(
        summary = "Update student",
        description = "Pass full student object with ID to update"
    )
    public ResponseEntity<Student> updateStudent(
            @RequestBody Student student) {
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping("/student/{id}")
    @Operation(
        summary = "Delete student by ID",
        description = "Pass student ID in URL to delete student"
    )
    public ResponseEntity<String> deleteStudent(
            @PathVariable Integer id) {
        return new ResponseEntity<>(
            "Student deleted with id " + id, HttpStatus.OK);
    }
}