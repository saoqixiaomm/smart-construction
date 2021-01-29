package com.xd.smartconstruction;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@MapperScan(basePackages = {"com.xd.smartconstruction.mapper"})
public class SmartConstructionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartConstructionApplication.class, args);
	}

}
