package co.edu.javeriana.discovery.pica.location;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PicaLocationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PicaLocationApplication.class, args);
	}

}
