package br.com.crja.api.gerentarefas.dto.request;

import br.com.crja.api.gerentarefas.entity.Departamento;
import br.com.crja.api.gerentarefas.entity.Pessoa;

public class PessoaRequestDTO {

	private String nome;
	private Long idDepartamento;

	public Pessoa transformaParaObjeto() {
		Departamento departamento = new Departamento();
		Pessoa pessoa = new Pessoa();

		departamento.setId(idDepartamento);
		pessoa.setNome(nome);
		pessoa.setIdDepartamento(departamento);
		
		return pessoa;
	}

	public String getNome() {
		return nome;
	}

	public Long getIdDepartamento() {
		return idDepartamento;
	}

}
