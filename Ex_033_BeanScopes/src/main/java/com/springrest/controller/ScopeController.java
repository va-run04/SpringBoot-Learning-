package com.springrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.service.PrototypeService;
import com.springrest.service.RequestService;
import com.springrest.service.SessionService;
import com.springrest.service.SingletonService;

@RestController
@RequestMapping("/api")
public class ScopeController {
	
	@Autowired
	private SingletonService singletonService;
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private SessionService sessionService;
	
	@Autowired
	private RequestService requestService;
	
	
	@GetMapping("/singleton")
	public ResponseEntity<String> getSingleton(){
		return new ResponseEntity<>(singletonService.getMessage(), HttpStatus.OK);
	}
	
	@GetMapping("/prototype")
	public ResponseEntity<String> getPrototype(){ 
		PrototypeService prototypeService = context.getBean(PrototypeService.class);
		return new ResponseEntity<>(prototypeService.getMessage(), HttpStatus.OK);
	}
	@GetMapping("/session")
	public ResponseEntity<String> getSession(){ 
        return new ResponseEntity<>(sessionService.getMessage(), HttpStatus.OK);
	}
	@GetMapping("/request")
	public ResponseEntity<String> getRequest(){ 
		return new ResponseEntity<>(requestService.getMessage(), HttpStatus.OK);
	}
	
}
