package org.oregami;

import org.axonframework.commandhandling.AsynchronousCommandBus;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.axonframework.spring.config.EnableAxon;
import org.axonframework.spring.config.EnableAxonAutoConfiguration;
import org.oregami.api.ChangeNameCommand;
import org.oregami.api.CreateGameCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableAxon
public class OregamiApplication {

	public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(OregamiApplication.class, args);

        CommandBus commandBus = context.getBean(CommandBus.class);
        commandBus.dispatch(GenericCommandMessage.asCommandMessage(new CreateGameCommand("42", "Tetris")));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        commandBus.dispatch(GenericCommandMessage.asCommandMessage(new ChangeNameCommand("42", "Tetris 2")));
        //commandBus.dispatch(GenericCommandMessage.asCommandMessage(new ChangeNameCommand("42", null)));
    }


	@Bean
    public EventStorageEngine eventStorageEngine() {
        return new InMemoryEventStorageEngine();
    }


    @Bean
    public CommandBus commandBus() {
        return new AsynchronousCommandBus();
    }
}
