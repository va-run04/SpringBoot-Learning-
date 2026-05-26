package com.springrest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrest.exception.StudentNotFoundException;
import com.springrest.model.Student;
import com.springrest.repository.StudentRepository;

@Service
public class StudentServiceImplementation implements StudentService {

    @Autowired
    private StudentRepository repository;

    @Override
    public Student saveStudent(Student student) {
        return repository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    @Override
    public Student getStudentById(Integer id) {
        return repository.findById(id)
            .orElseThrow(() -> new StudentNotFoundException(
                "Student not found with id " + id));
    }

    @Override
    public Student updateStudent(Student student) {
        Student existing = repository.findById(student.getId())
            .orElseThrow(() -> new StudentNotFoundException(
                "Student not found with id " + student.getId()));

        existing.setName(student.getName());
        existing.setCity(student.getCity());
        existing.setEmail(student.getEmail());

        return repository.save(existing);
    }

    @Override
    public Student updateStudentCity(Integer id, String city) {
        Student existing = repository.findById(id)
            .orElseThrow(() -> new StudentNotFoundException(
                "Student not found with id " + id));

        existing.setCity(city);

        return repository.save(existing);
    }

    @Override
    public void deleteStudent(Integer id) {
        repository.findById(id)
            .orElseThrow(() -> new StudentNotFoundException(
                "Student not found with id " + id));

        repository.deleteById(id);
    }
}