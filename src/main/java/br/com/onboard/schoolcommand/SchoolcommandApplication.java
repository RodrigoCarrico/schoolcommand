package br.com.onboard.schoolcommand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class SchoolcommandApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolcommandApplication.class, args);
    }

}
