package br.com.crja.api.gerentarefas.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

import static br.com.crja.api.gerentarefas.constant.BDConstants.*;

@Entity
@Table(name = TABLE_NAME_TAREFA)
public class Tarefa  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7384941182348696219L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = FIELD_NAME_TAREFA_ID)
	private Long id;

	@Column(name = FIELD_NAME_TAREFA_TITULO)
	private String titulo;

	@Column(name = FIELD_NAME_TAREFA_DESCRICAO)
	private String descricao;

	@Column(name = FIELD_NAME_TAREFA_PRAZO)
	private LocalDate prazo;

	@OneToOne(fetch = FetchType.EAGER, targetEntity = Departamento.class)
	@JoinColumn(name = FIELD_NAME_TAREFA_ID_DEPARTAMENTO)
	private Departamento idDepartamento;

	@Column(name = FIELD_NAME_TAREFA_DURACAO)
	private Long duracao;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Pessoa.class)
	@JoinColumn(name = FIELD_NAME_TAREFA_ID_PESSOA)
	private Pessoa idPessoa;

	@Column(name = FIELD_NAME_TAREFA_FINALIZADO)
	private Boolean finalizado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getPrazo() {
		return prazo;
	}

	public void setPrazo(LocalDate prazo) {
		this.prazo = prazo;
	}

	public Departamento getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(Departamento idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public Long getDuracao() {
		return duracao;
	}

	public void setDuracao(Long duracao) {
		this.duracao = duracao;
	}

	public Pessoa getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Pessoa idPessoa) {
		this.idPessoa = idPessoa;
	}

	public Boolean getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}

}
