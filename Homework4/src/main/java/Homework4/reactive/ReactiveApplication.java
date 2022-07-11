package Homework4.reactive;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class ReactiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveApplication.class, args);
	}

//	@Bean
//	public ConnectionFactoryInitializer initializer(@Qualifier("connectionFactory") ConnectionFactory connectionFactory) {
//		var initializer = new ConnectionFactoryInitializer();
//		initializer.setConnectionFactory(connectionFactory);
//
//		var populator = new CompositeDatabasePopulator();
//		populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
//		// populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("data.sql")));
//		initializer.setDatabasePopulator(populator);
//
//		return initializer;
//	}
}
