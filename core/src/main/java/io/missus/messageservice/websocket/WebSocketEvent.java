package io.missus.messageservice.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WebSocketEvent {
    private String eventId;
    private String eventDt;
}
