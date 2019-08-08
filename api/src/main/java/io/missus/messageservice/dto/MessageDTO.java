package io.missus.messageservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class MessageDTO implements Serializable {

    private String id = UUID.randomUUID().toString();
    private String username;
    private String message;
    private Date date = new Date();
}