package com.example.demo.repository;

import com.example.demo.entity.Usuari;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariRepository extends JpaRepository<Usuari, Long> {

}
