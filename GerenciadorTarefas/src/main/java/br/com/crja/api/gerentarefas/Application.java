package br.com.crja.api.gerentarefas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    private static final Logger LOGGER = LogManager.getLogger(Application.class);

    public static void main(String [] args){
        if(SpringApplication.run(Application.class).isActive()){
            LOGGER.info("success");
        }
    }
}
