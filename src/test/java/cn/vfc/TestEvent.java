package cn.vfc;

import cn.event.MessagePublisher;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class TestEvent {
    @Test
    public void test(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        MessagePublisher messagePublisher = applicationContext.getBean(MessagePublisher.class);
        messagePublisher.sendMessage("wo shi ni baba","hao erzi");
        Map<String, MessagePublisher> beansOfType = applicationContext.getBeansOfType(MessagePublisher.class);
        for(String key : beansOfType.keySet()){
            System.out.println(beansOfType.get(key));
        }
    }
}
