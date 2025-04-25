package fr.maaxow.accountMeBatch;

import org.springframework.batch.core.launch.support.CommandLineJobRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccountMeBatchApplication extends CommandLineJobRunner {

	public static void main(String[] args) {
		SpringApplication.run(AccountMeBatchApplication.class, args);
	}

}
