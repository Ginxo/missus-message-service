package io.missus.messageservice.service;

import io.missus.messageservice.data.entity.Message;
import io.missus.messageservice.data.repository.MessageRepository;
import io.missus.messageservice.dto.MessageDTO;
import io.missus.messageservice.mapper.MessageMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional(readOnly = true)
public class MessageService {

    private final MessageRepository repository;
    private final MessageMapper mapper;


    public MessageService(final MessageRepository repository, final MessageMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Flux<MessageDTO> getAll() {
        return repository.findAll().map(this.mapper::toDto);
    }

    public Mono<MessageDTO> getById(@PathVariable String id) {
        return repository.findById(id).map(this.mapper::toDto);
    }

    public Mono<MessageDTO> save(@RequestBody MessageDTO dto) {
        final Message message = this.mapper.toEntity(dto);
        return this.repository.save(message).map(this.mapper::toDto);
    }
}
