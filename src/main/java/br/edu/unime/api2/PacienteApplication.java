package br.edu.unime.api2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class PacienteApplication {

    public static void main(String[] args) {
        SpringApplication.run(PacienteApplication.class, args);
    }

}
