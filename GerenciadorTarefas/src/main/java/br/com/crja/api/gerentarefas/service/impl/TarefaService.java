package br.com.crja.api.gerentarefas.service.impl;

import static br.com.crja.api.gerentarefas.constant.MensagemConstants.SERVICE_PESSOA;
import static br.com.crja.api.gerentarefas.constant.MensagemConstants.SERVICE_TAREFA;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.crja.api.gerentarefas.entity.Departamento;
import br.com.crja.api.gerentarefas.entity.Pessoa;
import br.com.crja.api.gerentarefas.entity.Tarefa;
import br.com.crja.api.gerentarefas.exception.RegistroInexistenteException;
import br.com.crja.api.gerentarefas.repository.TarefaRepository;
import br.com.crja.api.gerentarefas.service.IDepartamentoService;
import br.com.crja.api.gerentarefas.service.IPessoaService;
import br.com.crja.api.gerentarefas.service.ITarefaService;

@Service
public class TarefaService implements ITarefaService {

	@Autowired
	private TarefaRepository repository;

	@Autowired
	private IDepartamentoService deptService;

	@Autowired
	private IPessoaService pessoaService;

	@Override
	public Tarefa adicionar(Tarefa tarefa) {

		Departamento depart = deptService.buscarPorId(tarefa.getIdDepartamento());

		if (tarefa.getIdPessoa().getId() == null) {
			tarefa.setIdPessoa(null);
		} else {
			Pessoa pessoa = pessoaService.buscarPorId(tarefa.getIdPessoa());
			tarefa.setIdPessoa(pessoa);
		}

		tarefa.setIdDepartamento(depart);

		return repository.save(tarefa);
	}

	@Override
	public Tarefa buscarPorId(Long id) {
		Optional<Tarefa> retorno = repository.findById(id);

		if (!retorno.isPresent()) {
			throw new RegistroInexistenteException(SERVICE_TAREFA);
		}

		return retorno.get();
	}

	@Override
	public void finalizarTarefa(Long id) {
		Tarefa tarefa = buscarPorId(id);
		tarefa.setFinalizado(true);
		repository.save(tarefa);
	}

	@Override
	public void alocarPessoa(Long idTarefa, Tarefa request) {
		Tarefa tarefa = buscarPorId(idTarefa);

		Departamento depart = deptService.buscarPorId(request.getIdDepartamento());

		if (request.getIdPessoa().getId() == null) {
			throw new RegistroInexistenteException(SERVICE_PESSOA);
		}

		if (tarefa.getIdDepartamento().equals(depart)) {
			Pessoa pessoa = pessoaService.buscarPorId(tarefa.getIdPessoa());
			tarefa.setIdPessoa(pessoa);
		}

		repository.save(tarefa);
	}

	@Override
	public Iterable<Tarefa> listar() {
		return repository.findAll();
	}

	@Override
	public List<Tarefa> buscarTarefasPorPessoa(Pessoa pessoa) {
		return repository.findByIdPessoa(pessoa);
	}

	@Override
	public List<Tarefa> buscarTarefasPorPrazoEPessoa(Pessoa pessoa, LocalDate prazoStart, LocalDate prazoEnd) {
		return repository.findByIdPessoaAndPrazoBetween(pessoa, prazoStart, prazoEnd);
	}

	@Override
	public List<Tarefa> listarTarefasSemAlocacao() {
		return repository.findTop3ByIdPessoaIsNullOrderByPrazoAsc();
	}

	@Override
	public Long totalTarefasPorDepartamento(Departamento idDepartamento) {
		Departamento depart = deptService.buscarPorId(idDepartamento);
		
		return repository.countByIdDepartamento(depart);
	}
}
