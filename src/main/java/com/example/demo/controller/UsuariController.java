package com.example.demo.controller;

import com.example.demo.entity.Usuari;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.exception.UsuariNotFoundException;
import com.example.demo.repository.UsuariRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UsuariController {
	
	@Autowired
	private UsuariRepository usuariRepository;

    @GetMapping("/usuaris")
    public List<Usuari> retrieveAllUsuaris() {
        return usuariRepository.findAll();
    }

    @GetMapping("/usuaris/{id}")
    public Usuari retrieveUsuari(@PathVariable long id) {
        Optional<Usuari> student = usuariRepository.findById(id);

        if (!student.isPresent()) {
            throw new UsuariNotFoundException("id-" + id);
        }
        return student.get();
    }

    @DeleteMapping("/usuaris/{id}")
    public void deleteUsuari(@PathVariable long id) {
        usuariRepository.deleteById(id);
    }

    @PostMapping("/usuaris")
    public ResponseEntity<Object> createUsuari(@RequestBody Usuari usuari) {
        Usuari savedUsuari = usuariRepository.save(usuari);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUsuari.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/usuaris/{id}")
    public ResponseEntity<Object> updateUsuari(@RequestBody Usuari usuari, @PathVariable long id) {

        Optional<Usuari> studentOptional = usuariRepository.findById(id);

        if (!studentOptional.isPresent())
            return ResponseEntity.notFound().build();

        usuari.setId(id);

        usuariRepository.save(usuari);

        return ResponseEntity.noContent().build();
    }
}
