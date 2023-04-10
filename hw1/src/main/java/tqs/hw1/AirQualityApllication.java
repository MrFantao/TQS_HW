package tqs.hw1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.SpringVersion;

@SpringBootApplication
public class AirQualityApllication {

    public static void main(String[] args) {
		System.out.println("version: " + SpringVersion.getVersion());
		SpringApplication.run(AirQualityApllication.class, args);
	}
    
}
