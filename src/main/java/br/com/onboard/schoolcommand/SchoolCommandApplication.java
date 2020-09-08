package br.com.onboard.schoolcommand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableMongoRepositories
public class SchoolCommandApplication {
    public static void main(String[] args) {
        SpringApplication.run(SchoolCommandApplication.class, args);
    }
}
