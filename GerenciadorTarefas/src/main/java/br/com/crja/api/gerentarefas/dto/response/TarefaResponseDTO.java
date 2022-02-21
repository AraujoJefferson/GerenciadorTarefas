package br.com.crja.api.gerentarefas.dto.response;

import java.time.format.DateTimeFormatter;

import org.springframework.util.StringUtils;

import br.com.crja.api.gerentarefas.constant.ApplicationConstants;
import br.com.crja.api.gerentarefas.entity.Pessoa;
import br.com.crja.api.gerentarefas.entity.Tarefa;

public class TarefaResponseDTO {

	private String titulo;
	private String descricao;
	private String prazo;
	private String departamento;
	private Long duracao;
	private String pessoa;
	private Boolean finalizado;

	private TarefaResponseDTO(String titulo, String descricao, String prazo, String departamento, Long duracao,
			String pessoa, Boolean finalizado) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.prazo = prazo;
		this.departamento = departamento;
		this.duracao = duracao;
		this.pessoa = pessoa;
		this.finalizado = finalizado;
	}

	public static TarefaResponseDTO transformaEmDTO(Tarefa tarefa) {
		Pessoa idPessoa = tarefa.getIdPessoa();
		String nome = "";
		
		if(!StringUtils.isEmpty(idPessoa)) {
			nome = idPessoa.getNome();
		}
		
		return new TarefaResponseDTO(tarefa.getTitulo(), tarefa.getDescricao(),
				tarefa.getPrazo().format(DateTimeFormatter.ofPattern(ApplicationConstants.DATE_PATTERN)),
				tarefa.getIdDepartamento().getTitulo(), tarefa.getDuracao(), nome,
				tarefa.getFinalizado());
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

	public String getDepartamento() {
		return departamento;
	}

	public Long getDuracao() {
		return duracao;
	}

	public String getPessoa() {
		return pessoa;
	}

	public Boolean getFinalizado() {
		return finalizado;
	}

}
