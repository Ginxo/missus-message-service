package io.missus.messageservice.controller;

import io.missus.messageservice.data.entity.Message;
import io.missus.messageservice.data.repository.MessageRepository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageRepository messageRepository;


    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping
    public Flux<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Message> getMessageById(@PathVariable String id) {
        return messageRepository.findById(id);
    }

    @PostMapping
    public Mono<Message> save(@RequestBody Message message) {
        return this.messageRepository.save(message);
    }
}
