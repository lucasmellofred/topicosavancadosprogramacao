package com.tpar.notasalunosprofessor.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.GeneratedValue;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;

@Container(containerName = "aluno")
public class Aluno {
	@Id
	@PartitionKey
	@GeneratedValue
	public String id;
	public String Nome;
	public String Sobrenome;
	public String Disciplina;
	public String Notas;
	public String Turma;
	public String Sala;

	@Transient
  private List<Nota> notas;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getSobrenome() {
		return Sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		Sobrenome = sobrenome;
	}
	public String getDisciplina() {
		return Disciplina;
	}
	public void setDisciplina(String disciplina) {
		Disciplina = disciplina;
	}
	public String getNotas() {
		return Notas;
	}
	public void setNotas(String notas) {
		Notas = notas;
	}
	public String getTurma() {
		return Turma;
	}
	public void setTurma(String turma) {
		Turma = turma;
	}
	public String getSala() {
		return Sala;
	}
	public void setSala(String sala) {
		Sala = sala;
	}

	
}