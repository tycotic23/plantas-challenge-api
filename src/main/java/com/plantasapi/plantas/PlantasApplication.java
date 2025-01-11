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
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PlantasApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlantasApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:4200/")
						.allowedMethods("*")
						.allowedHeaders("*");
			}
		};
	}

	@Bean
	public CommandLineRunner initData(UserRepository userRepository, AuthServiceImplement authService, FactoryRepository factoryRepository, SensorRepository sensorRepository, TypeSensorRepository typeSensorRepository){
		return (args -> {
			Usuario admin=new Usuario("jorge","jorgito@gmail.com","jorgito","asdasdas");
			Usuario user2=new Usuario("jorge2","jorgisdfsdfto@gmail.com","jorgito","asdasdas");
			Factory factory=new Factory("Argentina","hola","https://flagcdn.com/w320/gs.png");
			Factory factory2=new Factory("Argentina","hola","https://flagcdn.com/w320/gs.png");
			Sensor s1=new Sensor(23,54,62,0);
			Sensor s2=new Sensor(23,54,62,0);
			Sensor s3=new Sensor(23,54,62,0);
			Sensor s4=new Sensor(23,54,62,0);
			Sensor s5=new Sensor(23,54,62,1);

			TypeSensor type=new TypeSensor("Temperatura");
			TypeSensor type2=new TypeSensor("Presión");
			TypeSensor type3=new TypeSensor("Viento");
			TypeSensor type4=new TypeSensor("Niveles");
			TypeSensor type5=new TypeSensor("Energía");
			TypeSensor type6=new TypeSensor("Tensión");
			TypeSensor type7=new TypeSensor("Monóxido de carbono");
			TypeSensor type8=new TypeSensor("Otros gases");


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
			admin.addFactory(factory2);

			authService.register(admin);
			authService.register(user2);
			factoryRepository.save(factory);
			factoryRepository.save(factory2);
			typeSensorRepository.save(type);
			typeSensorRepository.save(type2);
			typeSensorRepository.save(type3);
			typeSensorRepository.save(type4);
			typeSensorRepository.save(type5);
			typeSensorRepository.save(type6);
			typeSensorRepository.save(type7);
			typeSensorRepository.save(type8);
			sensorRepository.save(s1);
			sensorRepository.save(s2);
			sensorRepository.save(s3);
			sensorRepository.save(s4);
			sensorRepository.save(s5);

		});
	}

}
