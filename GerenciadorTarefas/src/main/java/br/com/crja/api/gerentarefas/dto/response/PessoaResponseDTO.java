package br.com.crja.api.gerentarefas.dto.response;

import br.com.crja.api.gerentarefas.entity.Pessoa;

public class PessoaResponseDTO {
	private String nome;
	private String departamento;
	private Long totalHorasGasta;

	private PessoaResponseDTO(String nome, String departamento, Long totalHorasGasta) {
		this.nome = nome;
		this.departamento = departamento;
		this.totalHorasGasta = totalHorasGasta;
	}
	public static PessoaResponseDTO transformaEmDTO(Pessoa pessoa) {
		return transformaEmDTO(pessoa, null);
	}

	public static PessoaResponseDTO transformaEmDTO(Pessoa pessoa, Long total) {
		return new PessoaResponseDTO(pessoa.getNome(), pessoa.getIdDepartamento().getTitulo(), total);
	}

	public String getNome() {
		return nome;
	}

	public String getDepartamento() {
		return departamento;
	}

	public Long getTotalHorasGasta() {
		return totalHorasGasta;
	}

}
