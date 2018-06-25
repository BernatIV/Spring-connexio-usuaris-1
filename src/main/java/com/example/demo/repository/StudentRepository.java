package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.entity.Student;
import org.springframework.stereotype.Repository;

@Repository/*RestResource(path = "students2", collectionResourceRel = "students2")*/
public interface StudentRepository extends JpaRepository<Student, Long> {
	// Student findById(Long id);

    Student findByName(String name);
}

