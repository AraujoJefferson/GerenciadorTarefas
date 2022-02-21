package br.com.crja.api.gerentarefas.service;

import java.util.List;

import br.com.crja.api.gerentarefas.entity.Departamento;

public interface IDepartamentoService {
	Departamento buscarPorId(Departamento departamento);
	List listarDepartamento();
}
