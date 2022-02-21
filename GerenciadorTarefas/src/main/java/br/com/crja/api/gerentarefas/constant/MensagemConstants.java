package br.com.crja.api.gerentarefas.constant;

public class MensagemConstants {
    
	//Pessoa
	public static final String PESSOA_ADICIONAR = "Adicionar uma pessoa"; 
	public static final String PESSOA_ALTERAR = "Alterar uma pessoa";
	public static final String PESSOA_REMOVER = "Remover uma pessoa";
	public static final String PESSOA_MEDIA = "Buscar pessoas por nome e período, retorna média de horas gastas por tarefa";
	public static final String PESSOA_TOTAL = "Listar pessoas trazendo nome, departamento, total horas gastas nas tarefas";
	public static final String SERVICE_DEPARTAMENTO = "Departamento não existe nos registros do sistema!";
	public static final String SERVICE_PESSOA = "Pessoa não existe nos registros do sistema!";
	public static final String SERVICE_PESSOA_DATA_PATTERN = "Data fora de padrão, por favor seguir como " + ApplicationConstants.DATE_PATTERN;
	
	//Tarefa
	public static final String TAREFA_ADICIONAR = "Adicionar uma tarefa"; 
	public static final String TAREFA_ALOCAR = "Alocar uma pessoa na tarefa que tenha o mesmo departamento";
	public static final String TAREFA_FINALIZAR = "Finalizar a tarefa";
	public static final String TAREFA_SEM_ALOCACAO = "Listar 3 tarefas que estejam sem pessoa alocada com os prazos mais antigos.";
	public static final String SERVICE_TAREFA = "Tarefa não existe nos registros do sistema!";
	
	//Departamento
	public static final String DEPARTAMENTO_TOTAL = "Listar departamento e quantidade de pessoas e tarefas";
	
}
