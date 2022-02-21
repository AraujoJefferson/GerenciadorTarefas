package br.com.crja.api.gerentarefas.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static br.com.crja.api.gerentarefas.constant.BDConstants.*;

@Entity
@Table(name = TABLE_NAME_PESSOA)
public class Pessoa implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1706686505188891837L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = FIELD_NAME_PESSOA_ID)
	private Long id;

	@Column(name = FIELD_NAME_PESSOA_NOME)
	private String nome;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Departamento.class)
	@JoinColumn(name = FIELD_NAME_PESSOA_ID_DEPARTAMENTO)
	private Departamento idDepartamento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Departamento getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(Departamento idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

}
