package cn.vfc.test;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class TestSchedule {
    @Scheduled(initialDelay = 1000L,fixedRate = 2000L)
    public void test(){
        System.out.println(new Date());
    }
}
