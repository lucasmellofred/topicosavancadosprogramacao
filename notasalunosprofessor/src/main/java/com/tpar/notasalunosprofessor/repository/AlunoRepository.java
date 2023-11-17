package com.tpar.notasalunosprofessor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.tpar.notasalunosprofessor.entity.Aluno;

@Repository
public interface AlunoRepository extends CrudRepository<Aluno, String> {
}
