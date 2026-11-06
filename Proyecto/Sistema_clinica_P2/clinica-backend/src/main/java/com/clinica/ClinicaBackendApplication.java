package com.clinica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ClinicaBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClinicaBackendApplication.class, args);
    }
}
