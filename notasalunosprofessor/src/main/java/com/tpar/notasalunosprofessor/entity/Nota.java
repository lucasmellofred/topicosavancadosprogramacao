package com.tpar.notasalunosprofessor.entity;

import org.springframework.data.annotation.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.GeneratedValue;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;

@Container(containerName = "nota")
public class Nota {
	@Id
	@PartitionKey
	@GeneratedValue
	public String id;
	public String Materia;
	public String Disciplina;
	public String Aluno;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMateria() {
		return Materia;
	}
	public void setMateria(String materia) {
		Materia = materia;
	}
	public String getDisciplina() {
		return Disciplina;
	}
	public void setDisciplina(String disciplina) {
		Disciplina = disciplina;
	}
	public String getAluno() {
		return Aluno;
	}
	public void setAluno(String aluno) {
		this.Aluno = aluno;
	}
}