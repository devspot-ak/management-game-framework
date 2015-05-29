package biz.devspot.management.game.framework.model;

import biz.devspot.management.game.framework.data.MessageDO;
import java.util.Date;

public class Message<DO extends MessageDO> extends MGFEntity<DO>{

    public Message(String subject, String body){
        data.setSubject(subject);
        data.setBody(body);
        data.setTime(new Date());
        data.setRead(false);
    }
    
    public String getSubject(){
        return data.getSubject();
    }
    
    public String getBody(){
        return data.getBody();
    }
    
    public boolean hasBeenRead(){
        return data.getRead();
    }
    
    public void markAsRead(){
        data.setRead(true);
    }
    
    @Override
    protected DO createDataObject() {
        return (DO) new MessageDO();
    }

}
