package qa.thonnersten.henrietta.database.hello.blockbusterDatabaseApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class BlockbusterApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlockbusterApplication.class, args);
	}
}
