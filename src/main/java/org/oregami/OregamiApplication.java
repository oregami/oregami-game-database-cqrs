package org.oregami;

import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
/*@EnableAxon
@ComponentScan({"org.oregami", "org.oregami.game"})
*/
public class OregamiApplication {

    public static void main(String[] args) {

        org.slf4j.Logger logger = LoggerFactory.getLogger(OregamiApplication.class);


        logger.error("Message logged at ERROR level");
        logger.warn("Message logged at WARN level");
        logger.info("Message logged at INFO level");
        logger.debug("Message logged at DEBUG level");


        SpringApplication.run(OregamiApplication.class, args);

/*
        Configuration config = DefaultConfigurer.defaultConfiguration()
                .configureAggregate(Game.class)
                .configureEmbeddedEventStore(c -> new InMemoryEventStorageEngine())
                .buildConfiguration();

        config.start();
*/

//        CommandBus commandBus = config.commandBus();
//        String gameId = UUID.randomUUID().toString();
//        commandBus.dispatch(GenericCommandMessage.asCommandMessage(new CreateGameCommand(gameId, GameEntryType.GAME)));
//
//        String releaseGroupId = UUID.randomUUID().toString();
//        commandBus.dispatch(GenericCommandMessage.asCommandMessage(new AddReleaseGroupCommand(gameId, releaseGroupId, ReleaseGroupReason.ORIGINAL)));
//
//        logger.info("gameId" + gameId);

    }

    @Bean
    public EventStorageEngine eventStorageEngine() {
        return new InMemoryEventStorageEngine();
    }

    @Bean
    public EventStore eventStore(EventStorageEngine eventStorageEngine) {
        return new EmbeddedEventStore(eventStorageEngine);
    }



}
