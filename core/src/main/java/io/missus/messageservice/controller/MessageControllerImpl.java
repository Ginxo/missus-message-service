package io.missus.messageservice.controller;

import io.missus.messageservice.dto.MessageDTO;
import io.missus.messageservice.service.MessageService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/messages")
public class MessageControllerImpl implements MessageController {

    private final MessageService messageService;


    public MessageControllerImpl(final MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    @GetMapping
    public Flux<MessageDTO> getAll() {
        return messageService.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public Mono<MessageDTO> getById(@PathVariable String id) {
        return (Mono<MessageDTO>) messageService.getById(id);
    }

    @Override
    @PostMapping
    public Mono<MessageDTO> save(@RequestBody MessageDTO message) {
        return this.messageService.save(message);
    }
}
