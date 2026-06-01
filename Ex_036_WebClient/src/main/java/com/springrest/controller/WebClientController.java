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
import org.springframework.web.reactive.function.client.WebClient;

import com.springrest.model.Student;

@RestController
@RequestMapping("/consumer")
public class WebClientController {

    private String baseUrl = "http://localhost:8083/provider";

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudent(
            @PathVariable Integer id) {
        WebClient webClient = WebClient.create();
        Student student = webClient.get()
            .uri(baseUrl + "/student/" + id)
            .retrieve()
            .bodyToMono(Student.class)
            .block();
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        WebClient webClient = WebClient.create();
        List<Student> students = webClient.get()
            .uri(baseUrl + "/students")
            .retrieve()
            .bodyToFlux(Student.class)
            .collectList()
            .block();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping("/student")
    public ResponseEntity<String> addStudent(
            @RequestBody Student student) {
        WebClient webClient = WebClient.create();
        String result = webClient.post()
            .uri(baseUrl + "/student")
            .bodyValue(student)
            .retrieve()
            .bodyToMono(String.class)
            .block();
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}