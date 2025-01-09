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
			Usuario user2=new Usuario("jorge2","jorgito@gmail.com","jorgito","asdasdas");
			Factory factory=new Factory("Argentina","hola");
			Factory factory2=new Factory("Argentina","hola");
			Sensor s1=new Sensor(23,54,62,34);
			Sensor s2=new Sensor(23,54,62,34);
			Sensor s3=new Sensor(23,54,62,34);
			Sensor s4=new Sensor(23,54,62,34);
			Sensor s5=new Sensor(23,54,62,34);

			TypeSensor type=new TypeSensor("Fuego");
			TypeSensor type2=new TypeSensor("Agua");
			TypeSensor type3=new TypeSensor("Viento");


			factory.addSensor(s1);
			factory2.addSensor(s2);
			factory.addSensor(s3);
			factory2.addSensor(s4);
			factory.addSensor(s5);
			type.addSensor(s1);
			type.addSensor(s2);
			type2.addSensor(s3);
			type2.addSensor(s4);
			type3.addSensor(s5);
			admin.addFactory(factory);
			user2.addFactory(factory2);

			authService.register(admin);
			authService.register(user2);
			factoryRepository.save(factory);
			factoryRepository.save(factory2);
			typeSensorRepository.save(type);
			typeSensorRepository.save(type2);
			typeSensorRepository.save(type3);
			sensorRepository.save(s1);
		});
	}

}
