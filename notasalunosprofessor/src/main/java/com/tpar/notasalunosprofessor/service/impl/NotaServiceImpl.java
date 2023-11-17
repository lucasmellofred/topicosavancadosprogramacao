package com.tpar.notasalunosprofessor.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tpar.notasalunosprofessor.entity.Nota;
import com.tpar.notasalunosprofessor.repository.NotaRepository;
import com.tpar.notasalunosprofessor.service.NotaService;

@Service
public class NotaServiceImpl implements NotaService {
	
	@Autowired
	private NotaRepository repository;

	@Override
	public List<Nota> getAll() {
		var iterador = repository.findAll();
		List<Nota> listaNotas = new ArrayList<>();

		iterador.forEach(listaNotas::add);

		return listaNotas;
	}

	@Override
	public Nota getById(String id) {
		var nota = repository.findById(id);
		if(nota.isPresent())
			return nota.get();
		return null;
	}

	@Override
	public Nota save(Nota nota) {
		nota.setId(null);
		return repository.save(nota);
	}

	@Override
	public Nota update(String id, Nota nota) {
		var buscaNotaAntigo = repository.findById(id);
		if (buscaNotaAntigo.isPresent()){
			var notaAntigo = buscaNotaAntigo.get();

			notaAntigo.setMateria(nota.getMateria());
			notaAntigo.setDisciplina(nota.getDisciplina());
			notaAntigo.setAluno(nota.getAluno());
			
			return repository.save(notaAntigo);
		}
		return null;
	}

	@Override
	public Nota delete(String id) {
		var buscaNota = repository.findById(id);
		if (buscaNota.isPresent()){
			var nota = buscaNota.get();

			repository.delete(nota);

			return nota;
		}
		return null;
	}
}
