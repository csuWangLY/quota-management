package com.project.quotamanagement.quotamanagement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.project.quotamanagement.quotamanagement.mapper")
public class QuotamanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuotamanagementApplication.class, args);
	}

}
