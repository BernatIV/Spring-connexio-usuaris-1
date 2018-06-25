package com.example.demo.controller;

import com.example.demo.entity.Usuari;
import com.example.demo.repository.UsuariRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuariController {
	
	@Autowired
	private UsuariRepository usuariRepository;

    @GetMapping("/usuaris")
    public List<Usuari> retrieveAllUsuaris() {
        return usuariRepository.findAll();
    }
}
