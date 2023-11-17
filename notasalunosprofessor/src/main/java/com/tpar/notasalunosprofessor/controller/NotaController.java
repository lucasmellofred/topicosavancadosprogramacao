package com.tpar.notasalunosprofessor.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tpar.notasalunosprofessor.entity.Nota;
import com.tpar.notasalunosprofessor.service.NotaService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/notas")
public class NotaController {
  @Autowired
	private NotaService service;

	@GetMapping
	public ResponseEntity<List<Nota>> listaNotas(){
		var listaNotas = service.getAll();
		return 
			new ResponseEntity<List<Nota>>
			(listaNotas, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Nota> buscarNota(@PathVariable("id")  String id){
		var nota = service.getById(id);
		if(nota == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return 
			new ResponseEntity<Nota>
			(nota, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Nota> inserirNota(@RequestBody Nota nota){
		if(nota == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		nota = service.save(nota);
		return 
			new ResponseEntity<Nota>

			(nota, HttpStatus.OK);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Nota> atualizarNota(@PathVariable("id")  String id, @RequestBody Nota nota){
		if(nota == null || id == ""  || id == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		nota = service.update(id, nota);
		if(nota == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return 
			new ResponseEntity<Nota>
			(nota, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Nota> removerNota(@PathVariable("id")  String id){
		if(id == ""  || id == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var nota = service.delete(id);
		if(nota == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return 
			new ResponseEntity<Nota>
			(nota, HttpStatus.OK);
	}
}
