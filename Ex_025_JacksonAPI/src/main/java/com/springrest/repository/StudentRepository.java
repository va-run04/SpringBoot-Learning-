package com.springrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springrest.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
