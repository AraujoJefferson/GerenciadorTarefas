package br.com.crja.api.gerentarefas.controller;

import static br.com.crja.api.gerentarefas.constant.ApplicationConstants.CONTENT_TYPE;
import static br.com.crja.api.gerentarefas.constant.MensagemConstants.DEPARTAMENTO_TOTAL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.crja.api.gerentarefas.repository.DepartamentoRepository;
import br.com.crja.api.gerentarefas.service.IDepartamentoService;
import br.com.crja.api.gerentarefas.service.impl.DepartamentoService;

@RestController
@Validated
@RequestMapping(path = "/api/v1/departamentos")
public class DepartamentoController {

    private static final Logger LOGGER = LogManager.getLogger(DepartamentoController.class);
   
    @Autowired
    private IDepartamentoService service;

    @Autowired
    public DepartamentoController(DepartamentoRepository repository){
        this.service = new DepartamentoService(repository);
    }

    @RequestMapping(method = RequestMethod.GET, path ="/", consumes = CONTENT_TYPE)
    public ResponseEntity<?> listar(){
		LOGGER.info(DEPARTAMENTO_TOTAL);
		return new ResponseEntity<>(service.listarDepartamento(), HttpStatus.OK);
    }
}
