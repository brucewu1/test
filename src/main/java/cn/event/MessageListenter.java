package cn.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;

import java.util.Map;

public class MessageListenter implements ApplicationListener<MessageEvent>, ApplicationContextAware {
    private ApplicationContext applicationContext;
    @Override
    public void onApplicationEvent(MessageEvent messageEvent) {
        Map<String, MessagePublisher> beansOfType = applicationContext.getBeansOfType(MessagePublisher.class);
        for(String key : beansOfType.keySet()){
            System.out.println(beansOfType.get(key));
        }
    }
    private String notificationAddress;

    public void setNotificationAddress(String notificationAddress) {
        this.notificationAddress = notificationAddress;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
