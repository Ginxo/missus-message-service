package io.missus.messageservice.data.repository;

import io.missus.messageservice.data.entity.Message;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Mono;

public interface MessageRepository extends ReactiveCassandraRepository<Message, String> {
    Mono<Message> findById(String id);
}