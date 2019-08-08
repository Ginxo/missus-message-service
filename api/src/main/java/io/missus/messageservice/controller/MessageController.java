package io.missus.messageservice.controller;

import io.missus.messageservice.dto.MessageDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MessageController {
    @GetMapping
    Flux<MessageDTO> getAll();

    @GetMapping("/{id}")
    Mono<MessageDTO> getById(@PathVariable String id);

    @PostMapping
    Mono<MessageDTO> save(@RequestBody MessageDTO message);
}
