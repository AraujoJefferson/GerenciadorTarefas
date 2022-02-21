package br.com.crja.api.gerentarefas.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.crja.api.gerentarefas.entity.Departamento;
import br.com.crja.api.gerentarefas.entity.Pessoa;


@Repository
public interface PessoaRepository extends CrudRepository<Pessoa,Long> {
	List<Pessoa> findByNome(String nome);
	Long countByIdDepartamento(Departamento idDepartamento);
}
