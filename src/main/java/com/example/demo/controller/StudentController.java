package com.example.demo.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entity.Student;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.repository.StudentRepository;

	// Sinó, no es veu al navegador quan fas http://localhost:8080/students, el format JSON apareix buit.

@RestController
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;  
	
	@GetMapping("/students")
	public List<Student> retrieveAllStudents() {
		return studentRepository.findAll();
	}
	

	@GetMapping("/students/{id}")
	public Student retrieveStudent(@PathVariable long id) {
		//Optional<Student> student = studentRepository.findById(id);
		Optional<Student> student = studentRepository.findById(id);
		Student result = null;
		if(student.isPresent()) {
			result = student.get();
		}
		
		if (!student.isPresent()) {
			throw new StudentNotFoundException("id-" + id);
			//return student.get();
		}
		return result;
	}
	
	@DeleteMapping("/students/{id}")
	public void deleteStudent(@PathVariable long id) {
		studentRepository.deleteById(id);
	}
	
	@PostMapping("/students")
	public ResponseEntity<Object> createStudent(@RequestBody Student student) {
		Student savedStudent = studentRepository.save(student);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedStudent.getId()).toUri();

		return ResponseEntity.created(location).build();

	}

	/*
	@PutMapping("/students/{id}")
	public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable long id) {

		Optional<Student> studentOptional = studentRepository.findById(id);

		if (!studentOptional.isPresent())
			return ResponseEntity.notFound().build();

		student.setId(id);
		
		studentRepository.save(student);

		return ResponseEntity.noContent().build();
	}*/

	
	/*
	// @RequestMapping("/students")
	public List<Student> retrieveAllStudents() {
				
		for(Student student: studentRepository.findAll()) {
			System.out.println("->" + student);
		}
		
		return studentRepository.findAll();
	}*/
}
//List<Student> students = new ArrayList<Student>();
//for(Student student: studentRepository.findAll()) {
//	System.out.println("->" + student);
//	students.add(student);
//}
//
//return students;
