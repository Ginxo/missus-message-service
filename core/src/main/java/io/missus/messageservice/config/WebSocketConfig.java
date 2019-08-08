package io.missus.messageservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.cassandra.config.AbstractReactiveCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;

import java.util.HashMap;
import java.util.Map;

@EnableReactiveCassandraRepositories
@Profile("!test")
@Configuration
public class WebSocketConfig {

    @Value("${websocket.uri}")
    private String webSockerURI;

    @Autowired
    private WebSocketHandler webSocketHandler;

    @Bean
    public HandlerMapping webSocketHandlerMapping() {
        final Map<String, WebSocketHandler> map = new HashMap<>();
        map.put(this.webSockerURI, this.webSocketHandler);

        final SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
        handlerMapping.setOrder(1);
        handlerMapping.setUrlMap(map);
        return handlerMapping;
    }

}
