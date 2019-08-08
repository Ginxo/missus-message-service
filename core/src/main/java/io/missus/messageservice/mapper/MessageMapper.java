package io.missus.messageservice.mapper;

import io.missus.messageservice.data.entity.Message;
import io.missus.messageservice.dto.MessageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MessageMapper extends EntityMapper<MessageDTO, Message> {

    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);
    MessageDTO toDto(Message message);

    Message toEntity(MessageDTO message);

    default Message fromId(String id) {
        if (id == null) {
            return null;
        }
        Message message = new Message();
        message.setId(id);
        return message;
    }
}
