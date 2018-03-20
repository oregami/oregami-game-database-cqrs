package org.oregami;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.LocaleResolver;

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


    }

}
