package com.tpar.notasalunosprofessor.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.tpar.notasalunosprofessor.entity.Professor;
import com.tpar.notasalunosprofessor.repository.ProfessorRepository;
import com.tpar.notasalunosprofessor.service.ProfessorService;

import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;

@Service
public class ProfessorServiceImpl implements ProfessorService {
	
	@Autowired
	private ProfessorRepository repository;
	private DaprClient client = new DaprClientBuilder().build();
    @Value("${app.component.topic.carro}")
    private String TOPIC_NAME;
    @Value("${app.component.service}")
	private String PUBSUB_NAME;

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

	/*@Override
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
	}*/

	@Override
    public Professor update(String id, Professor professor) {
        var buscaProfessorAntigo = repository.findById(id);
        if (buscaProfessorAntigo.isPresent()){
            var professorAntigo = buscaProfessorAntigo.get();

            //Atualizar cada atributo do objeto antigo 
            professorAntigo.setNome(professor.getNome());
            professorAntigo = repository.save(professorAntigo);
            publicarAtualizacao(professorAntigo);
            return professorAntigo;
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

	//método privado para publicar a atualização
    private void publicarAtualizacao(Professor professor){
        client.publishEvent(
					PUBSUB_NAME,
					TOPIC_NAME,
					professor).block();
    }

}
