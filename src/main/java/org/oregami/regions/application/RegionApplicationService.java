package org.oregami.regions.application;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.oregami.gamingEnvironments.command.CreateGamingEnvironmentCommand;
import org.oregami.regions.RegionId;
import org.oregami.regions.command.CreateRegionCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * Created by sebastian on 28.02.17.
 */
@Service
public class RegionApplicationService {

    /**
     *
     */
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private CommandGateway commandGateway;

    public CompletableFuture<Object> createNewRegion(String name, boolean isCountry, boolean isBusinessRegion, String description) {
        return commandGateway.send(new CreateRegionCommand(new RegionId(name), isCountry, isBusinessRegion, description));
    }







}
