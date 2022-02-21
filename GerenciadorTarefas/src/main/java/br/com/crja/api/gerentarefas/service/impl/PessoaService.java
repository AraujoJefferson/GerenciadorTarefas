package br.com.crja.api.gerentarefas.service.impl;

import static br.com.crja.api.gerentarefas.constant.MensagemConstants.SERVICE_PESSOA;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.crja.api.gerentarefas.dto.response.PessoaResponseDTO;
import br.com.crja.api.gerentarefas.entity.Departamento;
import br.com.crja.api.gerentarefas.entity.Pessoa;
import br.com.crja.api.gerentarefas.entity.Tarefa;
import br.com.crja.api.gerentarefas.exception.RegistroInexistenteException;
import br.com.crja.api.gerentarefas.repository.PessoaRepository;
import br.com.crja.api.gerentarefas.service.IDepartamentoService;
import br.com.crja.api.gerentarefas.service.IPessoaService;
import br.com.crja.api.gerentarefas.service.ITarefaService;

@Service
public class PessoaService implements IPessoaService {

	@Autowired
	private PessoaRepository repository;

	@Autowired
	private IDepartamentoService deptService;

	@Autowired
	private ITarefaService tarefaService;

	public PessoaService(PessoaRepository repository) {
		this.repository = repository;
	}

	@Override
	public Pessoa buscarPorId(Pessoa pessoa) {
		Long id = pessoa.getId();

		if (StringUtils.isEmpty(id)) {
			throw new RegistroInexistenteException(SERVICE_PESSOA);
		}

		Optional<Pessoa> optPessoa = repository.findById(id);

		if (!optPessoa.isPresent()) {
			throw new RegistroInexistenteException(SERVICE_PESSOA);
		}

		return optPessoa.get();
	}

	@Override
	public Pessoa adicionar(Pessoa pessoa) {
		Departamento depart = deptService.buscarPorId(pessoa.getIdDepartamento());

		pessoa.setIdDepartamento(depart);

		return repository.save(pessoa);
	}

	@Override
	public Pessoa alterar(Pessoa pessoa) {
		buscarPorId(pessoa);
		return adicionar(pessoa);
	}

	@Override
	public void remover(Pessoa pessoa) {
		buscarPorId(pessoa);
		repository.deleteById(pessoa.getId());
	}

	@Override
	public Iterable<Pessoa> listar() {
		return repository.findAll();
	}

	@Override
	public Long totalPessoasPorDepartamento(Departamento idDepartamento) {
		Departamento depart = deptService.buscarPorId(idDepartamento);
		
		return repository.countByIdDepartamento(depart);
	}

	@Override
	public List<PessoaResponseDTO> listarPessoas() {
		Iterable<Pessoa> pessoas = repository.findAll();
		List<PessoaResponseDTO> retorno = new ArrayList<>();

		pessoas.forEach((Pessoa pessoa) -> {
			long totalHorasGastasTarefa = tarefaService.buscarTarefasPorPessoa(pessoa).stream()
					.mapToLong(Tarefa::getDuracao).sum();

			retorno.add(PessoaResponseDTO.transformaEmDTO(pessoa, totalHorasGastasTarefa));
		});
		return retorno;
	}

	@Override
	public List<PessoaResponseDTO> listarPessoasNome(String nome, LocalDate inicio, LocalDate fim) {

		Iterable<Pessoa> pessoas = repository.findByNome(nome);
		List<PessoaResponseDTO> retorno = new ArrayList<>();

		pessoas.forEach((Pessoa s) -> {
			Double totalHorasGastasTarefaMedia = tarefaService.buscarTarefasPorPrazoEPessoa(s, inicio, fim).stream()
					.map(Tarefa::getDuracao).mapToDouble(Double::longBitsToDouble).average().getAsDouble();

			retorno.add(PessoaResponseDTO.transformaEmDTO(s, Double.doubleToRawLongBits(totalHorasGastasTarefaMedia)));
		});
		return retorno;
	}

}
