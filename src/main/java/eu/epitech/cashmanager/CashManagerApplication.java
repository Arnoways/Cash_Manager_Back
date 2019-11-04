package eu.epitech.cashmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class CashManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CashManagerApplication.class, args);
	}

}
