package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import server.repositories.AddressRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "server.repositories")
@EntityScan(basePackages = "entities")
public class AssServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AssServerApplication.class, args);
    }

}

