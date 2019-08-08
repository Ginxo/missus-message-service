package io.missus.messageservice;

import io.missus.messageservice.data.entity.Message;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Collections;

public class MessageServiceClient {
    private WebClient client;
    private Mono<Message> messageMono;

    MessageServiceClient() {
        this.client = WebClient
                .builder()
                .baseUrl("http://localhost:8080")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
                .build();

        this.messageMono = this.client.get()
                .uri("/messages")
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(Message.class));
    }

    public static void main(String... args) {
        MessageServiceClient client = new MessageServiceClient();
        client.messageMono.subscribe(System.out::println);
    }


}
