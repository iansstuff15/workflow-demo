package dev.byian.workflow;

import dev.byian.workflow.config.RsaKeyProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class WorkflowApplication implements CommandLineRunner {
    public static void main(String[] args) {
		SpringApplication.run(WorkflowApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
