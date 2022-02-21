package br.com.crja.api.gerentarefas.dto.request;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.util.StringUtils;

import br.com.crja.api.gerentarefas.entity.Departamento;
import br.com.crja.api.gerentarefas.entity.Pessoa;
import br.com.crja.api.gerentarefas.entity.Tarefa;

public class TarefaRequestDTO {

	private String titulo;
	private String descricao;
	private String prazo;
	private Long idDepartamento;
	private Long duracao;
	private Long idPessoa;
	private Boolean finalizado;

	public Tarefa transformaParaObjeto() {

		Departamento departamento = new Departamento();
		Tarefa tarefa = new Tarefa();

		departamento.setId(idDepartamento);

		Pessoa pessoa = new Pessoa();
		pessoa.setId(idPessoa);
		tarefa.setIdPessoa(pessoa);

		tarefa.setTitulo(titulo);
		tarefa.setDescricao(descricao);
		if (!StringUtils.isEmpty(prazo)) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate prazoParse = LocalDate.parse(prazo, formatter);
			tarefa.setPrazo(prazoParse);
		}
		tarefa.setIdDepartamento(departamento);
		tarefa.setDuracao(duracao);
		tarefa.setFinalizado(finalizado);

		return tarefa;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getPrazo() {
		return prazo;
	}

	public Long getIdDepartamento() {
		return idDepartamento;
	}

	public Long getDuracao() {
		return duracao;
	}

	public Long getIdPessoa() {
		return idPessoa;
	}

	public Boolean getFinalizado() {
		return finalizado;
	}

}
