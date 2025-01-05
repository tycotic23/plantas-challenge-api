package com.plantasapi.plantas;

import com.plantasapi.plantas.models.Factory;
import com.plantasapi.plantas.models.Usuario;
import com.plantasapi.plantas.repositories.FactoryRepository;
import com.plantasapi.plantas.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PlantasApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlantasApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(UserRepository userRepository, FactoryRepository factoryRepository){
		return (args -> {
			Usuario admin=new Usuario("jorge","jorgito@gmail.com","jorgito","asdasdas");
			Factory factory=new Factory("Argentina","hola");
			admin.addFactory(factory);
			userRepository.save(admin);
			factoryRepository.save(factory);
		});
	}

}
