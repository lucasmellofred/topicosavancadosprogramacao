package com.tpar.notasalunosprofessor.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.tpar.notasalunosprofessor.entity.Aluno;

@Service
public interface AlunoService {
	public List<Aluno> getAll();
	public Aluno getById(String id);
	public Aluno save(Aluno aluno);
	public Aluno update(String id, Aluno aluno);
	public Aluno delete(String id);
}
