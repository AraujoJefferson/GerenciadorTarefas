package br.com.crja.api.gerentarefas.constant;

public class BDConstants {
    //EndPoints
    public static final String CONTENT_TYPE = "application/json";

    //Pessoa DataBase
    public static final String TABLE_NAME_PESSOA = "Pessoa";
    public static final String FIELD_NAME_PESSOA_ID = "id";
    public static final String FIELD_NAME_PESSOA_ID_DEPARTAMENTO = "id_departamento";
    public static final String FIELD_NAME_PESSOA_NOME = "nome";
    
    //Tarefa DataBase
    public static final String TABLE_NAME_TAREFA = "Tarefa";
    public static final String FIELD_NAME_TAREFA_ID = "id";
    public static final String FIELD_NAME_TAREFA_TITULO = "titulo";
    public static final String FIELD_NAME_TAREFA_DESCRICAO = "descricao";
    public static final String FIELD_NAME_TAREFA_PRAZO = "prazo";
    public static final String FIELD_NAME_TAREFA_ID_DEPARTAMENTO = "id_departamento";
    public static final String FIELD_NAME_TAREFA_DURACAO = "duracao";
    public static final String FIELD_NAME_TAREFA_ID_PESSOA = "id_pessoa";
    public static final String FIELD_NAME_TAREFA_FINALIZADO = "finalizado";

    //Departamento DataBase
    public static final String TABLE_NAME_DEPART = "Departamento";
    public static final String FIELD_NAME_DEPART_ID = "id";
    public static final String FIELD_NAME_DEPART_TITULO = "titulo";


}
