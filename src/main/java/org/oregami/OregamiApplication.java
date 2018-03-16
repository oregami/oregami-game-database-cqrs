package org.oregami;

import org.oregami.regions.application.RegionApplicationService;
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
