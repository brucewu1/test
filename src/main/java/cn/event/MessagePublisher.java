package cn.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import java.util.List;
public class MessagePublisher implements ApplicationEventPublisherAware {
    private List<String> blackList;
    private ApplicationEventPublisher applicationEventPublisher;

    public void setBlackList(List<String> blackList) {
        this.blackList = blackList;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void sendMessage(String message,String content){
        if(blackList.contains(message)){
            applicationEventPublisher.publishEvent(new MessageEvent(this,message,content));
        }
    }
}
