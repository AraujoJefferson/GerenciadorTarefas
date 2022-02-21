package br.com.crja.api.gerentarefas.controller;

import static br.com.crja.api.gerentarefas.constant.ApplicationConstants.CONTENT_TYPE;
import static br.com.crja.api.gerentarefas.constant.MensagemConstants.TAREFA_ADICIONAR;
import static br.com.crja.api.gerentarefas.constant.MensagemConstants.TAREFA_ALOCAR;
import static br.com.crja.api.gerentarefas.constant.MensagemConstants.TAREFA_FINALIZAR;
import static br.com.crja.api.gerentarefas.constant.MensagemConstants.TAREFA_SEM_ALOCACAO;

import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.crja.api.gerentarefas.dto.request.TarefaRequestDTO;
import br.com.crja.api.gerentarefas.dto.response.TarefaResponseDTO;
import br.com.crja.api.gerentarefas.entity.Tarefa;
import br.com.crja.api.gerentarefas.service.ITarefaService;

@RestController
@Validated
@RequestMapping(path = "/api/v1/tarefas")
public class TarefaController {

	private static final Logger LOGGER = LogManager.getLogger(TarefaController.class);

	@Autowired
	private ITarefaService service;

	@RequestMapping(method = RequestMethod.POST, path = "/", consumes = CONTENT_TYPE)
	public ResponseEntity<TarefaResponseDTO> adicionar(@RequestBody TarefaRequestDTO tarefaDTO) {
		LOGGER.info(TAREFA_ADICIONAR);
		Tarefa adicionar = service.adicionar(tarefaDTO.transformaParaObjeto());
		return new ResponseEntity<>(TarefaResponseDTO.transformaEmDTO(adicionar), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/alocar/{id}", consumes = CONTENT_TYPE)
	public ResponseEntity<?> alocar(@PathVariable("id") @NotNull Long idTarefa, @RequestBody TarefaRequestDTO tarefaDTO) {
		LOGGER.info(TAREFA_ALOCAR);
		
		service.alocarPessoa(idTarefa, tarefaDTO.transformaParaObjeto());
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/finalizar/{id}", consumes = CONTENT_TYPE)
	public ResponseEntity<?> finalizar(@PathVariable("id") @NotNull Long id) {
		LOGGER.info(TAREFA_FINALIZAR);

		service.finalizarTarefa(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/pendentes", consumes = CONTENT_TYPE)
	public ResponseEntity<?> listarTarefasNaoAlocada() {
		LOGGER.info(TAREFA_SEM_ALOCACAO);
		return new ResponseEntity<>(service.listarTarefasSemAlocacao(), HttpStatus.OK);
	}
}
