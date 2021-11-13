package co.edu.javeriana.discovery.pica.location;

import brave.sampler.Sampler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class PicaLocationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PicaLocationApplication.class, args);
	}

	@Bean
	public Sampler sampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}
