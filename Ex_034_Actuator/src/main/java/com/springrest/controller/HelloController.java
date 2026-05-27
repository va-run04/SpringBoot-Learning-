package com.springrest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class HelloController {

	@GetMapping("/hello")
	public ResponseEntity<String> Hello(){
		return new ResponseEntity<>("Hello from actuator app", HttpStatus.OK);
	}
	
	@GetMapping("/status")
    public ResponseEntity<String> status() {
        return new ResponseEntity<>("App is running", HttpStatus.OK);
    }

}
