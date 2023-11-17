package com.tpar.notasalunosprofessor.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.tpar.notasalunosprofessor.entity.Nota;

@Service
public interface NotaService {
	public List<Nota> getAll();
	public Nota getById(String id);
	public Nota save(Nota nota);
	public Nota update(String id, Nota nota);
	public Nota delete(String id);
}
