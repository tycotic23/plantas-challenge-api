package com.plantasapi.plantas;

import com.plantasapi.plantas.models.Factory;
import com.plantasapi.plantas.models.Sensor;
import com.plantasapi.plantas.models.TypeSensor;
import com.plantasapi.plantas.models.Usuario;
import com.plantasapi.plantas.repositories.FactoryRepository;
import com.plantasapi.plantas.repositories.SensorRepository;
import com.plantasapi.plantas.repositories.TypeSensorRepository;
import com.plantasapi.plantas.repositories.UserRepository;
import com.plantasapi.plantas.services.implement.AuthServiceImplement;
import com.plantasapi.plantas.services.implement.UserServiceImplement;
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
	public CommandLineRunner initData(UserRepository userRepository, AuthServiceImplement authService, FactoryRepository factoryRepository, SensorRepository sensorRepository, TypeSensorRepository typeSensorRepository){
		return (args -> {
			Usuario admin=new Usuario("jorge","jorgito@gmail.com","jorgito","asdasdas");
			Factory factory=new Factory("Argentina","hola");
			Sensor s1=new Sensor(23,54,62,34);
			TypeSensor type=new TypeSensor("Fuego");

			factory.addSensor(s1);
			type.addSensor(s1);
			admin.addFactory(factory);

			authService.register(admin);
			factoryRepository.save(factory);
			typeSensorRepository.save(type);
			sensorRepository.save(s1);
		});
	}

}
