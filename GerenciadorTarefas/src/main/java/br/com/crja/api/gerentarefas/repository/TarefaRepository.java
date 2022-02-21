package br.com.crja.api.gerentarefas.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.crja.api.gerentarefas.entity.Departamento;
import br.com.crja.api.gerentarefas.entity.Pessoa;
import br.com.crja.api.gerentarefas.entity.Tarefa;


@Repository
public interface TarefaRepository extends CrudRepository<Tarefa,Long> {

	Long countByIdDepartamento(Departamento idDepartamento); 
	List <Tarefa> findByIdPessoa(Pessoa pessoa);
	List<Tarefa> findByIdPessoaAndPrazoBetween(Pessoa pessoa, LocalDate prazoStart, LocalDate prazoEnd) ;
	List<Tarefa> findTop3ByIdPessoaIsNullOrderByPrazoAsc();
}
