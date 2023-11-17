package com.tpar.notasalunosprofessor.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tpar.notasalunosprofessor.entity.Professor;
import com.tpar.notasalunosprofessor.repository.ProfessorRepository;
import com.tpar.notasalunosprofessor.service.ProfessorService;

@Service
public class ProfessorServiceImpl implements ProfessorService {
	
	@Autowired
	private ProfessorRepository repository;

	@Override
	public List<Professor> getAll() {
		var iterador = repository.findAll();
		List<Professor> listaProfessores = new ArrayList<>();

		iterador.forEach(listaProfessores::add);

		return listaProfessores;
	}

	@Override
	public Professor getById(String id) {
		var professor = repository.findById(id);
		if(professor.isPresent())
			return professor.get();
		return null;
	}

	@Override
	public Professor save(Professor professor) {
		professor.setId(null);
		return repository.save(professor);
	}

	@Override
	public Professor update(String id, Professor professor) {
		var buscaProfessorAntigo = repository.findById(id);
		if (buscaProfessorAntigo.isPresent()){
			var professorAntigo = buscaProfessorAntigo.get();

			professorAntigo.setNome(professor.getNome());
			professorAntigo.setSobrenome(professor.getSobrenome());
			professorAntigo.setDisciplina(professor.getDisciplina());
			professorAntigo.setNota(professor.getNota());
			professorAntigo.setTurma(professor.getTurma());
			professorAntigo.setTurno(professor.getTurno());
			professorAntigo.setCurso(professor.getCurso());
			
			return repository.save(professorAntigo);
		}
		return null;
	}

	@Override
	public Professor delete(String id) {
		var buscaProfessor = repository.findById(id);
		if (buscaProfessor.isPresent()){
			var professor = buscaProfessor.get();

			repository.delete(professor);

			return professor;
		}
		return null;
	}

}
