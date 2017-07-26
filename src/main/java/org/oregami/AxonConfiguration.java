package org.oregami;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.correlation.CorrelationDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sebastian on 26.06.17.
 */
@Configuration
public class AxonConfiguration {

/*
    @Autowired
    public void registerInterceptors(CommandBus commandBus) {
        if (commandBus instanceof SimpleCommandBus) {
            Map<String, String> m = new HashMap<>();
            m.put("userId","zzz");
            ((SimpleCommandBus) commandBus).registerDispatchInterceptor(
                    (messages) ->
                            (index, message) -> message.andMetaData(m)
            );
        }
    }
*/

    @Bean
    public CorrelationDataProvider correlationDataProvider() {
        return new CorrelationDataProvider() {
            @Override
            public Map<String, ?> correlationDataFor(Message<?> message) {
                Map<String, Object> m = new HashMap<>();
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (authentication!=null && authentication.isAuthenticated()) {
                    m.put("userId", authentication.getPrincipal());
                }
                return m;
            }
        };
    }
}
