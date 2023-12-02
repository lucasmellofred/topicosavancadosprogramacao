package com.tpar.notasalunosprofessor.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.tpar.notasalunosprofessor.entity.Aluno;
import com.tpar.notasalunosprofessor.repository.AlunoRepository;
import com.tpar.notasalunosprofessor.service.AlunoService;

import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;

@Service
public class AlunoServiceImpl implements AlunoService {

	@Autowired
	private AlunoRepository repository;

	private DaprClient client = new DaprClientBuilder().build();
    @Value("${app.component.topic.aluno}")
    private String TOPIC_NAME;
    @Value("${app.component.service}")
	private String PUBSUB_NAME;

	//método privado para publicar a atualização
    private void publicarAtualizacao(Aluno aluno){
        client.publishEvent(
					PUBSUB_NAME,
					TOPIC_NAME,
					aluno).block();
    }


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

	/*Override
	public Aluno save(Aluno aluno) {
		aluno.setId(null);
		//return repository.save(aluno);
		aluno = repository.save(aluno);
		publicarAtualizacao(aluno);
		return aluno;
	}*/

	@Override
	public Aluno save(Aluno aluno) {
		aluno.setId(null);
		return repository.save(aluno);
	}

	@Override
	public Aluno update(String id, Aluno aluno) {
		var buscaAlunoAntigo = repository.findById(id);
		/*if (buscaAlunoAntigo.isPresent()){
			var alunoAntigo = buscaAlunoAntigo.get();

			alunoAntigo.setNome(aluno.getNome());
			alunoAntigo.setSobrenome(aluno.getSobrenome());
			alunoAntigo.setDisciplina(aluno.getDisciplina());
			alunoAntigo.setNotas(aluno.getNotas());
			alunoAntigo.setTurma(aluno.getTurma());
			alunoAntigo.setSala(aluno.getSala());
			
			return repository.save(alunoAntigo);
		}*/
		if (buscaAlunoAntigo.isPresent()){
            var alunoAntigo = buscaAlunoAntigo.get();

            //Atualizar cada atributo do objeto antigo 
            alunoAntigo.setNome(aluno.getNome());
            alunoAntigo = repository.save(alunoAntigo);
            publicarAtualizacao(alunoAntigo);
            return alunoAntigo;
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

	@Override
	public Aluno update(Aluno aluno) {
		return repository.save(aluno);
	}
}
