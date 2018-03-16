package org.oregami;

import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;
import org.oregami.regions.application.RegionApplicationService;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@SpringBootApplication
@ComponentScan({"org.oregami", "org.oregami.game"})
public class OregamiApplication {


    public static void main(String[] args) {



        org.slf4j.Logger logger = LoggerFactory.getLogger(OregamiApplication.class);
        logger.error("Message logged at ERROR level");
        logger.warn("Message logged at WARN level");
        logger.info("Message logged at INFO level");
        logger.debug("Message logged at DEBUG level");

        ConfigurableApplicationContext context = SpringApplication.run(OregamiApplication.class, args);

        System.out.println("LocaleResolver: " + context.getBean(LocaleResolver.class).toString());

        RegionApplicationService regionApplicationService = context.getBean(RegionApplicationService.class);
        regionApplicationService.createNewRegion("EUROPE", true, false, "Europe");
        regionApplicationService.createNewRegion("UNITED_STATES", true, false, "UNITED_STATES");
        regionApplicationService.createNewRegion("UNITED_KINGDOM", true, false, "UNITED_KINGDOM");
        regionApplicationService.createNewRegion("FRANCE", true, false, "FRANCE");
        regionApplicationService.createNewRegion("JAPAN", true, false, "JAPAN");
        regionApplicationService.createNewRegion("CHINA", true, false, "CHINA");
        regionApplicationService.createNewRegion("NORTH_AMERICA", true, false, "NORTH_AMERICA");


    }

}
