package com.tpar.notasalunosprofessor.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.GeneratedValue;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;

@Container(containerName = "professor",autoCreateContainer = true)
public class Professor {
	@Id
	@PartitionKey
	@GeneratedValue
	public String id;
	public String Nome;
	public String Sobrenome;
	public String Disciplina;
	public String Nota;
	public String Turma;
	public String Turno;
	public String Curso;
	public String alunoId;

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
	public String getNota() {
		return Nota;
	}
	public void setNota(String nota) {
		Nota = nota;
	}
	public String getTurma() {
		return Turma;
	}
	public void setTurma(String turma) {
		Turma = turma;
	}
	public String getTurno() {
		return Turno;
	}
	public void setTurno(String turno) {
		Turno = turno;
	}
	public String getCurso() {
		return Curso;
	}
	public void setCurso(String curso) {
		Curso = curso;
	}
	public String getAlunoId() {
		return alunoId;
	}
	public void setAlunoId(String alunoId) {
		this.alunoId = alunoId;
	}
	public List<Nota> getNotas() {
		return notas;
	}
	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}	
	
}