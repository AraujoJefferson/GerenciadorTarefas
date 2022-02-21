package br.com.crja.api.gerentarefas.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.crja.api.gerentarefas.dto.request.PessoaRequestDTO;
import br.com.crja.api.gerentarefas.dto.response.PessoaResponseDTO;
import br.com.crja.api.gerentarefas.entity.Pessoa;
import br.com.crja.api.gerentarefas.exception.DataIncorretaException;
import br.com.crja.api.gerentarefas.service.IPessoaService;

import static br.com.crja.api.gerentarefas.constant.MensagemConstants.*;
import static br.com.crja.api.gerentarefas.constant.ApplicationConstants.CONTENT_TYPE;

@RestController
@Validated
@RequestMapping(path = "/api/v1/pessoas")
public class PessoaController {

	private static final Logger LOGGER = LogManager.getLogger(PessoaController.class);

	@Autowired
	private IPessoaService service;

	@RequestMapping(method = RequestMethod.POST, path = "/", consumes = CONTENT_TYPE)
	public ResponseEntity<PessoaResponseDTO> adicionar(@RequestBody PessoaRequestDTO pessoaDTO) {
		LOGGER.info(PESSOA_ADICIONAR);
		Pessoa adicionar = service.adicionar(pessoaDTO.transformaParaObjeto());
		return new ResponseEntity<>(PessoaResponseDTO.transformaEmDTO(adicionar), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/{id}", consumes = CONTENT_TYPE)
	public ResponseEntity<PessoaResponseDTO> alterar(@PathVariable("id") @NotNull Long id,
			@RequestBody PessoaRequestDTO pessoaDTO) {
		LOGGER.info(PESSOA_ALTERAR);
		Pessoa transformaParaObjeto = pessoaDTO.transformaParaObjeto();

		transformaParaObjeto.setId(id);

		Pessoa alterar = service.alterar(transformaParaObjeto);

		return new ResponseEntity<>(PessoaResponseDTO.transformaEmDTO(alterar), HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/{id}", consumes = CONTENT_TYPE)
	public ResponseEntity<PessoaResponseDTO> remover(@PathVariable("id") @NotNull Long id) {
		LOGGER.info(PESSOA_REMOVER);
		Pessoa pessoa = new Pessoa();
		pessoa.setId(id);
		
		service.remover(pessoa);

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, path = "/", consumes = CONTENT_TYPE)
	public ResponseEntity<List<?>> listarPessoas() {
		LOGGER.info(PESSOA_TOTAL);
		return new ResponseEntity<>(service.listarPessoas(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/gastos", consumes = CONTENT_TYPE)
	public ResponseEntity<List<?>> mediaHorasGastasPorTarefa(@RequestParam("nome") String nome,
			@RequestParam("dtInicio") @DateTimeFormat(pattern = "YYYY-MM-DD") String dtInicio,
			@RequestParam("dtFim") @DateTimeFormat(pattern = "YYYY-MM-DD") String dtFim) {
		LOGGER.info(PESSOA_MEDIA);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dateInicio = LocalDate.parse(dtInicio, formatter);
		LocalDate dateFim = LocalDate.parse(dtFim, formatter);

		if (!dateInicio.isBefore(dateFim)) {
			throw new DataIncorretaException("Data inicio maior que data fim!");
		}

		return new ResponseEntity<>(service.listarPessoasNome(nome, dateInicio, dateFim), HttpStatus.OK);
	}

}
