package com.springrest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.springrest.model.Student;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    private String baseUrl = "http://localhost:8081/provider";

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudent(
            @PathVariable Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = baseUrl + "/student/" + id;
        ResponseEntity<Student> response =
            restTemplate.getForEntity(url, Student.class);
        Student student = response.getBody();
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/students")
    public ResponseEntity<List> getAllStudents() {
        RestTemplate restTemplate = new RestTemplate();
        String url = baseUrl + "/students";
        ResponseEntity<List> response =
            restTemplate.getForEntity(url, List.class);
        List students = response.getBody();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping("/student")
    public ResponseEntity<String> addStudent(
            @RequestBody Student student) {
        RestTemplate restTemplate = new RestTemplate();
        String url = baseUrl + "/student";
        ResponseEntity<String> response =
            restTemplate.postForEntity(url, student, String.class);
        String result = response.getBody();
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}