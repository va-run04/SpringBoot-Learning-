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

    // PUT — update full student
    @PutMapping("/student")
    public ResponseEntity<Student> updateStudent(
            @RequestBody Student student) {
        Student updated = service.updateStudent(student);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // PATCH — update only city
    @PatchMapping("/student/{id}/{city}")
    public ResponseEntity<Student> updateStudentCity(
            @PathVariable Integer id,
            @PathVariable String city) {
        Student updated = service.updateStudentCity(id, city);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // DELETE — delete student
    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> deleteStudent(
            @PathVariable Integer id) {
        service.deleteStudent(id);
        return new ResponseEntity<>(
            "Student deleted successfully", HttpStatus.OK);
    }
}