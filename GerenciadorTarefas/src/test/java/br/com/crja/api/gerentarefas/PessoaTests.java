package br.com.crja.api.gerentarefas;

import static br.com.crja.api.gerentarefas.constant.ApplicationConstants.CONTENT_TYPE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PessoaTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void t1ListarPessoas() throws Exception {
		mockMvc.perform(get("/api/v1/pessoas/").contentType(CONTENT_TYPE)).andExpect(status().isOk())
				.andExpect(content().json("[\n" + "    {\n" + "        \"nome\": \"Camila\",\n"
						+ "        \"departamento\": \"Financeiro\",\n" + "        \"totalHorasGasta\": 20\n"
						+ "    },\n" + "    {\n" + "        \"nome\": \"Pedro\",\n"
						+ "        \"departamento\": \"Comercial\",\n" + "        \"totalHorasGasta\": 5\n" + "    },\n"
						+ "    {\n" + "        \"nome\": \"Fabiano\",\n"
						+ "        \"departamento\": \"Desenvolvimento\",\n" + "        \"totalHorasGasta\": 2\n"
						+ "    },\n" + "    {\n" + "        \"nome\": \"Raquel\",\n"
						+ "        \"departamento\": \"Desenvolvimento\",\n" + "        \"totalHorasGasta\": 2\n"
						+ "    },\n" + "    {\n" + "        \"nome\": \"Patricia\",\n"
						+ "        \"departamento\": \"Desenvolvimento\",\n" + "        \"totalHorasGasta\": 6\n"
						+ "    },\n" + "    {\n" + "        \"nome\": \"Joaquim\",\n"
						+ "        \"departamento\": \"Financeiro\",\n" + "        \"totalHorasGasta\": 14\n"
						+ "    }\n" + "]"))
				.andReturn();
	}

	@Test
	public void  t2MediaHorasGastasPorTarefaDataInvalida() throws Exception {
		String nome = "Camila";
		String dtInicio = "Camila";
		String dtFim = "Camila";

		mockMvc.perform(get("/api/v1/pessoas/gastos?nome=" + nome + "&dtFim=" + dtFim + "&dtInicio=" + dtInicio)
				.contentType(CONTENT_TYPE)).andExpect(status().isBadRequest())
				.andExpect(content().json(
						"{\"status\":400,\"detail\":\"Data fora de padrão, por favor seguir como yyyy-MM-dd\",\"developerMessage\":\"java.time.format.DateTimeParseException\"}"))
				.andReturn();
	}

	@Test
	public void t3MediaHorasGastasPorTarefaDtFimMenorDtInicio() throws Exception {
		String nome = "Camila";
		String dtInicio = "2022-02-15";
		String dtFim = "2022-01-31";

		mockMvc.perform(get("/api/v1/pessoas/gastos?nome=" + nome + "&dtFim=" + dtFim + "&dtInicio=" + dtInicio)
				.contentType(CONTENT_TYPE)).andExpect(status().isBadRequest())
				.andExpect(content().json(
						"{\"status\":400,\"detail\":\"Data inicio maior que data fim!\",\"developerMessage\":\"br.com.crja.api.gerentarefas.exception.DataIncorretaException\"}"))
				.andReturn();
	}

	@Test
	public void  t4MediaHorasGastasPorTarefa() throws Exception {
		String nome = "Camila";
		String dtFim = "2022-02-15";
		String dtInicio = "2022-01-31";
		mockMvc.perform(get("/api/v1/pessoas/gastos?nome=" + nome + "&dtFim=" + dtFim + "&dtInicio=" + dtInicio)
				.contentType(CONTENT_TYPE)).andExpect(status().isOk())
				.andExpect(content().json("[\n" + "    {\n" + "        \"nome\": \"Camila\",\n"
						+ "        \"departamento\": \"Financeiro\",\n" + "        \"totalHorasGasta\": 10\n"
						+ "    }\n" + "]"))
				.andReturn();
	}

	@Test
	public void t5AdicionarPessoa() throws Exception {
		mockMvc.perform(post("/api/v1/pessoas/")
				.contentType(CONTENT_TYPE).content("{ \"nome\" : \"Jefferson\", \"idDepartamento\" : 3 }"))
				.andExpect(status().isCreated())
				.andExpect(content().json("{\"nome\":\"Jefferson\",\"departamento\":\"Desenvolvimento\",\"totalHorasGasta\":null}"))
				.andReturn();
	}

	@Test
	public void t6AlterarPessoa() throws Exception {
		int idPessoa = 1;
		mockMvc.perform(put("/api/v1/pessoas/" + idPessoa)
				.contentType(CONTENT_TYPE).content("{ \"nome\" : \"Jefferson\", \"idDepartamento\" : 3 }"))
				.andExpect(status().isOk())
				.andExpect(content().json("{\n" + "    \"nome\": \"Jefferson\",\n"
						+ "    \"departamento\": \"Desenvolvimento\",\n" + "    \"totalHorasGasta\": null\n" + "}"))
				.andReturn();
	}

	@Test
	public void t7AlterarPessoasInexistente() throws Exception {
		int idPessoa = 25;
		int idDepartamento = 3;
		mockMvc.perform(put("/api/v1/pessoas/" + idPessoa).contentType(CONTENT_TYPE)
				.content("{ \"nome\" : \"Jefferson\", \"idDepartamento\" : " + idDepartamento + " }"))
				.andExpect(status().isNotFound())
				.andExpect(content().json("{\n" + "    \"status\": 404,\n"
						+ "    \"detail\": \"Pessoa não existe nos registros do sistema!\",\n"
						+ "    \"developerMessage\": \"br.com.crja.api.gerentarefas.exception.RegistroInexistenteException\"\n"
						+ "}"))
				.andReturn();
	}

	@Test
	public void t8AlterarPessoasDepartamentoInexistente() throws Exception {
		int idPessoa = 1;
		int idDepartamento = 25;
		mockMvc.perform(put("/api/v1/pessoas/" + idPessoa).contentType(CONTENT_TYPE)
				.content("{ \"nome\" : \"Jefferson\", \"idDepartamento\" : " + idDepartamento + " }"))
				.andExpect(status().isNotFound())
				.andExpect(content().json("{\n" + "    \"status\": 404,\n"
						+ "    \"detail\": \"Departamento não existe nos registros do sistema!\",\n"
						+ "    \"developerMessage\": \"br.com.crja.api.gerentarefas.exception.RegistroInexistenteException\"\n"
						+ "}"))
				.andReturn();
	}

	@Test
	public void t9RemoverUsuarioInexistente() throws Exception {
		int idPessoa = 25;
		mockMvc.perform(delete("/api/v1/pessoas/" + idPessoa).contentType(CONTENT_TYPE))
				.andExpect(status().isNotFound())
				.andExpect(content().json("{\"status\":404,\"detail\":\"Pessoa não existe nos registros do sistema!\",\"developerMessage\":\"br.com.crja.api.gerentarefas.exception.RegistroInexistenteException\"}"))
				.andReturn();
	}
	
	@Test
	public void t10ListarTarefasPendentes() throws Exception {
		mockMvc.perform(get("/api/v1/tarefas/pendentes/").contentType(CONTENT_TYPE)).andExpect(status().isOk())
				.andExpect(content().json("[\n"
						+ "    {\n"
						+ "        \"id\": 1004,\n"
						+ "        \"titulo\": \"Reunião A\",\n"
						+ "        \"descricao\": \"Reunião com cliente A para apresentação do produto\",\n"
						+ "        \"prazo\": \"2022-02-05\",\n"
						+ "        \"idDepartamento\": {\n"
						+ "            \"id\": 2,\n"
						+ "            \"titulo\": \"Comercial\"\n"
						+ "        },\n"
						+ "        \"duracao\": 5,\n"
						+ "        \"idPessoa\": null,\n"
						+ "        \"finalizado\": false\n"
						+ "    },\n"
						+ "    {\n"
						+ "        \"id\": 1005,\n"
						+ "        \"titulo\": \"Reunião final\",\n"
						+ "        \"descricao\": \"Fechamento contrato\",\n"
						+ "        \"prazo\": \"2022-03-28\",\n"
						+ "        \"idDepartamento\": {\n"
						+ "            \"id\": 2,\n"
						+ "            \"titulo\": \"Comercial\"\n"
						+ "        },\n"
						+ "        \"duracao\": 6,\n"
						+ "        \"idPessoa\": null,\n"
						+ "        \"finalizado\": false\n"
						+ "    },\n"
						+ "    {\n"
						+ "        \"id\": 1002,\n"
						+ "        \"titulo\": \"Bug 352\",\n"
						+ "        \"descricao\": \"Corrigir bug 352 na versão 1.25\",\n"
						+ "        \"prazo\": \"2022-05-10\",\n"
						+ "        \"idDepartamento\": {\n"
						+ "            \"id\": 3,\n"
						+ "            \"titulo\": \"Desenvolvimento\"\n"
						+ "        },\n"
						+ "        \"duracao\": 25,\n"
						+ "        \"idPessoa\": null,\n"
						+ "        \"finalizado\": false\n"
						+ "    }\n"
						+ "]"))
				.andReturn();
	}

	@Test
	public void t11AdicionarTarefa() throws Exception {
		mockMvc.perform(post("/api/v1/tarefas/")
				.contentType(CONTENT_TYPE).content("{\n"
						+ "    \"titulo\" : \"Bug 400\",\n"
						+ "    \"descricao\" : \"Abrindo contrato\",\n"
						+ "    \"prazo\" : \"2022-02-21\",\n"
						+ "    \"idDepartamento\" : 1,\n"
						+ "    \"duracao\" : 91,\n"
						+ "    \"idPessoa\" : null,\n"
						+ "    \"finalizado\" : true\n"
						+ "}"))
				.andExpect(status().isCreated())
				.andExpect(content().json("{\n"
						+ "    \"titulo\": \"Bug 400\",\n"
						+ "    \"descricao\": \"Abrindo contrato\",\n"
						+ "    \"prazo\": \"2022-02-21\",\n"
						+ "    \"departamento\": \"Financeiro\",\n"
						+ "    \"duracao\": 91,\n"
						+ "    \"pessoa\": \"\",\n"
						+ "    \"finalizado\": true\n"
						+ "}"))
				.andReturn();
	}

	@Test
	public void t12AlterarFinalizarTarefaInexistente() throws Exception {
		int idPessoa = 1025;
		mockMvc.perform(put("/api/v1/tarefas/finalizar/" + idPessoa).contentType(CONTENT_TYPE))
				.andExpect(status().isNotFound())
				.andExpect(content().json("{\n"
						+ "    \"status\": 404,\n"
						+ "    \"detail\": \"Tarefa não existe nos registros do sistema!\",\n"
						+ "    \"developerMessage\": \"br.com.crja.api.gerentarefas.exception.RegistroInexistenteException\"\n"
						+ "}"))
				.andReturn();
	}

	@Test
	public void t13AlterarAlocarTarefa() throws Exception {
		int idPessoa = 1010;
		mockMvc.perform(put("/api/v1/tarefas/alocar/" + idPessoa)
				.contentType(CONTENT_TYPE).content("{\n"
						+ "    \"titulo\" : \"Bug 400\",\n"
						+ "    \"idDepartamento\" : 1,\n"
						+ "    \"duracao\" : 91,\n"
						+ "    \"idPessoa\" : 1\n"
						+ "}"))
				.andExpect(status().isCreated())
				.andReturn();
	}
}
