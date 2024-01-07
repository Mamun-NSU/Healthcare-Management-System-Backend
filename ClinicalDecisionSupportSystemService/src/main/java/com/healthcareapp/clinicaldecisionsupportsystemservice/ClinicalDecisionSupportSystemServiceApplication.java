package com.healthcareapp.clinicaldecisionsupportsystemservice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@SpringBootApplication
@EnableFeignClients
public class ClinicalDecisionSupportSystemServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ClinicalDecisionSupportSystemServiceApplication.class, args);
	}

}

