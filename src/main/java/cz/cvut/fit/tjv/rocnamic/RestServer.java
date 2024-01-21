package cz.cvut.fit.tjv.rocnamic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class RestServer {

	public static void main(String[] args) {
		SpringApplication.run(RestServer.class, args);
	}

}
