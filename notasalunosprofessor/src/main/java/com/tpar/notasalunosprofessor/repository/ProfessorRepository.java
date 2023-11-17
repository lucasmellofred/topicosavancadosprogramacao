package com.tpar.notasalunosprofessor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.tpar.notasalunosprofessor.entity.Professor;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor, String> {
}
