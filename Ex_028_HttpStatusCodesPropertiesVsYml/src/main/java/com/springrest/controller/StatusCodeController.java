package com.springrest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.model.Student;

@RestController
@RequestMapping("/status")
public class StatusCodeController {

    // 200 OK — request success
    @GetMapping("/200")
    public ResponseEntity<String> status200() {
        return new ResponseEntity<>("200 - OK - Request successful", HttpStatus.OK);
    }

    // 201 Created — new resource created
    @PostMapping("/201")
    public ResponseEntity<Student> status201(@RequestBody Student student) {
        // simulating save
        student.setId(1);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    // 204 No Content — success but nothing to return
    @DeleteMapping("/204")
    public ResponseEntity<Void> status204() {
        // simulating delete
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 400 Bad Request — client sent invalid data
    @PostMapping("/400")
    public ResponseEntity<String> status400(@RequestBody Student student) {
        if (student.getName() == null || student.getName().isEmpty()) {
            return new ResponseEntity<>(
                "400 - Bad Request - Name cannot be empty",
                HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Student is valid", HttpStatus.OK);
    }

    // 401 Unauthorized — not logged in
    @GetMapping("/401")
    public ResponseEntity<String> status401(
            @RequestHeader(value = "token", required = false) String token) {
        if (token == null) {
            return new ResponseEntity<>(
                "401 - Unauthorized - Please login first",
                HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("Welcome!", HttpStatus.OK);
    }

    // 403 Forbidden — logged in but no permission
    @GetMapping("/403")
    public ResponseEntity<String> status403(
            @RequestHeader(value = "role", required = false) String role) {
        if (role == null || !role.equals("ADMIN")) {
            return new ResponseEntity<>(
                "403 - Forbidden - You dont have permission",
                HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>("Welcome Admin!", HttpStatus.OK);
    }

    // 404 Not Found — resource does not exist
    @GetMapping("/404/{id}")
    public ResponseEntity<String> status404(@PathVariable Integer id) {
        // simulating student not found
        if (id > 100) {
            return new ResponseEntity<>(
                "404 - Not Found - Student with id " + id + " not found",
                HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Student found with id " + id, HttpStatus.OK);
    }

    // 405 Method Not Allowed — wrong HTTP method
    // This happens automatically when you call POST on a GET endpoint
    // Example: POST http://localhost:8080/status/200 → 405

    // 409 Conflict — duplicate resource
    @PostMapping("/409")
    public ResponseEntity<String> status409(@RequestBody Student student) {
        // simulating duplicate email check
        if (student.getEmail().equals("rohan@gmail.com")) {
            return new ResponseEntity<>(
                "409 - Conflict - Email already exists",
                HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Student registered", HttpStatus.CREATED);
    }

    // 500 Internal Server Error — server crashed
    @GetMapping("/500")
    public ResponseEntity<String> status500() {
        try {
            // simulating server crash
            int result = 10 / 0;  // ArithmeticException
            return new ResponseEntity<>("Result: " + result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                "500 - Internal Server Error - " + e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}