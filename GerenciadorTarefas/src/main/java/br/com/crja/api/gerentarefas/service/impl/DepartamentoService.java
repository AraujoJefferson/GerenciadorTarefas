package br.com.crja.api.gerentarefas.service.impl;

import static br.com.crja.api.gerentarefas.constant.MensagemConstants.SERVICE_DEPARTAMENTO;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.crja.api.gerentarefas.entity.Departamento;
import br.com.crja.api.gerentarefas.exception.RegistroInexistenteException;
import br.com.crja.api.gerentarefas.repository.DepartamentoRepository;
import br.com.crja.api.gerentarefas.service.IDepartamentoService;
import br.com.crja.api.gerentarefas.service.IPessoaService;
import br.com.crja.api.gerentarefas.service.ITarefaService;

@Service
public class DepartamentoService implements IDepartamentoService {

	@Autowired
	private DepartamentoRepository repository;
	
	@Autowired
	private IPessoaService pessoaService;

	@Autowired
	private ITarefaService tarefaService;

	public DepartamentoService(DepartamentoRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<?> listarDepartamento() {
		return StreamSupport.stream(repository.findAll().spliterator(), false)
				.map(depart -> {
					HashMap<String, String> dp = new HashMap<>();
					dp.put("departamento", depart.getTitulo());
					dp.put("totalPessoasPorDepartamento", pessoaService.totalPessoasPorDepartamento(depart).toString());
					dp.put("totalTarefasPorDepartamento", tarefaService.totalTarefasPorDepartamento(depart).toString());
					return dp;
				}).collect(Collectors.toList());
	}

	@Override
	public Departamento buscarPorId(Departamento departamento) {
		Long id = departamento.getId();

		if (StringUtils.isEmpty(id)) {
			throw new RegistroInexistenteException(SERVICE_DEPARTAMENTO);
		}

		Optional<Departamento> depart = repository.findById(id);

		if (!depart.isPresent()) {
			throw new RegistroInexistenteException(SERVICE_DEPARTAMENTO);
		}

		return depart.get();
	}

}
