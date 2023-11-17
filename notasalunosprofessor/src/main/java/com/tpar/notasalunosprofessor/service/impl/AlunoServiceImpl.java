package com.tpar.notasalunosprofessor.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tpar.notasalunosprofessor.entity.Aluno;
import com.tpar.notasalunosprofessor.repository.AlunoRepository;
import com.tpar.notasalunosprofessor.service.AlunoService;

@Service
public class AlunoServiceImpl implements AlunoService {

	@Autowired
	private AlunoRepository repository;

	@Override
	public List<Aluno> getAll() {
		var iterador = repository.findAll();
		List<Aluno> listaAlunos = new ArrayList<>();

		iterador.forEach(listaAlunos::add);

		return listaAlunos;
	}

	@Override
	public Aluno getById(String id) {
		var aluno = repository.findById(id);
		if(aluno.isPresent())
			return aluno.get();
		return null;
	}

	@Override
	public Aluno save(Aluno aluno) {
		aluno.setId(null);
		return repository.save(aluno);
	}

	@Override
	public Aluno update(String id, Aluno aluno) {
		var buscaAlunoAntigo = repository.findById(id);
		if (buscaAlunoAntigo.isPresent()){
			var alunoAntigo = buscaAlunoAntigo.get();

			alunoAntigo.setNome(aluno.getNome());
			alunoAntigo.setSobrenome(aluno.getSobrenome());
			alunoAntigo.setDisciplina(aluno.getDisciplina());
			alunoAntigo.setNotas(aluno.getNotas());
			alunoAntigo.setTurma(aluno.getTurma());
			alunoAntigo.setSala(aluno.getSala());
			
			return repository.save(alunoAntigo);
		}
		return null;
	}

	@Override
	public Aluno delete(String id) {
		var buscaAluno = repository.findById(id);
		if (buscaAluno.isPresent()){
			var aluno = buscaAluno.get();

			repository.delete(aluno);

			return aluno;
		}
		return null;
	}
}
