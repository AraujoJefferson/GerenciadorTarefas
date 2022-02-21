package br.com.crja.api.gerentarefas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.crja.api.gerentarefas.entity.Departamento;


@Repository
public interface DepartamentoRepository extends CrudRepository<Departamento,Long> {

}
