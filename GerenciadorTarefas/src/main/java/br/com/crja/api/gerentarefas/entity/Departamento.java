package br.com.crja.api.gerentarefas.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import static br.com.crja.api.gerentarefas.constant.BDConstants.*;

@Entity
@Table(name = TABLE_NAME_DEPART)
public class Departamento implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3980472750298079252L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = FIELD_NAME_DEPART_ID)
	private Long id;
	
	@Column(name = FIELD_NAME_DEPART_TITULO)
	private String titulo;
	
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

}
