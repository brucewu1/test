package cn.event;

import org.springframework.context.ApplicationEvent;

public class MessageEvent extends ApplicationEvent {
    private final String address;
    private final String content;
    public MessageEvent(Object source,String address,String content) {
        super(source);
        this.address = address;
        this.content = content;
    }
}
