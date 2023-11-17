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
import com.tpar.notasalunosprofessor.entity.Aluno;
import com.tpar.notasalunosprofessor.service.AlunoService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
	@Autowired
	private AlunoService service;

	@GetMapping
	public ResponseEntity<List<Aluno>> listaAlunos(){
		var listaAlunos = service.getAll();
		return 
			new ResponseEntity<List<Aluno>>
			(listaAlunos, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Aluno> buscarAluno(@PathVariable("id")  String id){
		var aluno = service.getById(id);
		if(aluno == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return 
			new ResponseEntity<Aluno>
			(aluno, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Aluno> inserirAluno(@RequestBody Aluno aluno){
		if(aluno == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		aluno = service.save(aluno);
		return 
			new ResponseEntity<Aluno>

			(aluno, HttpStatus.OK);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Aluno> atualizarAluno(@PathVariable("id")  String id, @RequestBody Aluno aluno){
		if(aluno == null || id == ""  || id == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		aluno = service.update(id, aluno);
		if(aluno == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return 
			new ResponseEntity<Aluno>
			(aluno, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Aluno> removerAluno(@PathVariable("id")  String id){
		if(id == ""  || id == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var aluno = service.delete(id);
		if(aluno == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return 
			new ResponseEntity<Aluno>
			(aluno, HttpStatus.OK);
	}
}
