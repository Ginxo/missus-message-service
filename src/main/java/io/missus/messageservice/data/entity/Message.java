package io.missus.messageservice.data.entity;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@Table
public class Message implements Serializable {

    @PrimaryKey
    private String id = UUID.randomUUID().toString();
    private String username;
    private String message;
    private Date date;
}