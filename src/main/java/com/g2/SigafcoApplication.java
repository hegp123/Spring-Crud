package com.g2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling //esto habilita para ejecutar tareas automaticas,  lo que es cronjob en java  TaskComponent.java, tiene una
public class SigafcoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SigafcoApplication.class, args);
	}

}
