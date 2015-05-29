package biz.devspot.management.game.framework.builder;

import biz.devspot.management.game.framework.model.Message;

public class MessageBuilder implements EntityBuilder<Message> {

    private String subject;
    private String body;

    public MessageBuilder(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

    @Override
    public Message build() {
        Message message = new Message(subject, body);
        return message;
    }

}
