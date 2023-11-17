package com.tpar.notasalunosprofessor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.tpar.notasalunosprofessor.entity.Nota;

@Repository
public interface NotaRepository extends CrudRepository<Nota, String> {
}
