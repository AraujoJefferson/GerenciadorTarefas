package br.com.crja.api.gerentarefas;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.crja.api.gerentarefas.repository.PessoaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PessoaRepository repository;

    @Before
    public void deleteAllBeforeTests() throws Exception {
        repository.deleteAll();
    }

    /*@Test
    public void createIssue() throws Exception {
        repository.deleteAll();
        mockMvc.perform(post("/")
                .header(EVENT_ISSUES,"issues")
                .contentType(CONTENT_TYPE)
                .content(getIssue()))
                .andExpect(status().isOk());
    }

    @Test
    public void createIssueComment() throws Exception {
        repository.deleteAll();
        mockMvc.perform(post("/")
                .header(EVENT_ISSUES,"issue_comment")
                .contentType(CONTENT_TYPE)
                .content(getIssue()))
                .andExpect(status().isOk());
    }

    @Test
    public void ping() throws Exception {
        mockMvc.perform(post("/")
                .header(EVENT_ISSUES,"ping")
                .contentType(CONTENT_TYPE)
        ).andExpect(status().isOk());
    }

    @Test
    public void searchIssues() throws Exception {
        repository.deleteAll();

//        Issues issues = new Issues();
//        issues.setNumber(1L);
//        issues.setAction("opened");
//        issues.setCreated_at("2019-01-25T23:31:35Z");
//
////        repository.save(issues);
//
//        mockMvc.perform(get("/issues/" + issues.getNumber() + "/events")
//                .contentType(CONTENT_TYPE))
//                .andExpect(status().isOk())
//                .andExpect(
//                        content().json("[{\"id\":" + issues.getId() + ",\"number\":1,\"action\":\"opened\"," +
//                                "\"created_at\":\"2019-01-25T23:31:35Z\"}]"))
//                .andReturn();
    }

    @Test
    public void searchIssueNotExist() throws Exception {
        repository.deleteAll();

        mockMvc.perform(get("/issues/9999/events")
                .contentType(CONTENT_TYPE))
                .andExpect(status().isNotFound());
    }
    
    private String getIssue(){
        String retorno =  new String("{\n" +
                "  \"action\": \"edited\",\n" +
                "  \"issue\": {\n" +
                "    " +
                "  }\n" +
                "}");
        return retorno;
    }*/
}

