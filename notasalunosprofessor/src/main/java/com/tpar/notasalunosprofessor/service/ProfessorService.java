package com.tpar.notasalunosprofessor.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.tpar.notasalunosprofessor.entity.Professor;

@Service
public interface ProfessorService {
	public List<Professor> getAll();
	public Professor getById(String id);
	public Professor save(Professor professor);
	public Professor update(String id, Professor professor);
	public Professor delete(String id);
}
