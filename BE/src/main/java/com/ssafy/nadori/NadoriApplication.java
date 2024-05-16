package com.ssafy.nadori;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.ssafy.nadori.model.dao")
@SpringBootApplication
public class NadoriApplication {

	public static void main(String[] args) {
		SpringApplication.run(NadoriApplication.class, args);
	}

}
