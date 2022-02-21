package br.com.crja.api.gerentarefas.service;

import java.time.LocalDate;
import java.util.List;

import br.com.crja.api.gerentarefas.entity.Departamento;
import br.com.crja.api.gerentarefas.entity.Pessoa;
import br.com.crja.api.gerentarefas.entity.Tarefa;

public interface ITarefaService {
	
	Tarefa adicionar(Tarefa tarefa);
	Tarefa buscarPorId(Long id);
	void finalizarTarefa(Long id);
	void alocarPessoa(Long idTarefa, Tarefa request); 
	List<Tarefa> listarTarefasSemAlocacao();
	Iterable<Tarefa> listar();
	List<Tarefa> buscarTarefasPorPessoa(Pessoa idPessoa);
	List<Tarefa> buscarTarefasPorPrazoEPessoa(Pessoa idPessoa, LocalDate prazoStart, LocalDate prazoEnd);
	Long totalTarefasPorDepartamento(Departamento depart);
}
