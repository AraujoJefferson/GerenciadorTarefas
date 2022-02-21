package br.com.crja.api.gerentarefas.service;

import java.time.LocalDate;
import java.util.List;

import br.com.crja.api.gerentarefas.dto.response.PessoaResponseDTO;
import br.com.crja.api.gerentarefas.entity.Departamento;
import br.com.crja.api.gerentarefas.entity.Pessoa;

public interface IPessoaService {
	
	Pessoa buscarPorId(Pessoa pessoa);
	Pessoa adicionar(Pessoa pessoa);
	Pessoa alterar(Pessoa pessoa);
	void remover(Pessoa pessoa);
    Iterable<Pessoa> listar();
    Long totalPessoasPorDepartamento(Departamento idDepartamento);
    List<PessoaResponseDTO> listarPessoas();
    List<PessoaResponseDTO> listarPessoasNome(String nome, LocalDate inicio, LocalDate fim);
}
