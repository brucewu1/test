package cn.vfc.mode.proxy;

import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
        /*Bird bird = new Bird();
        BirdLogProxy birdLogProxy = new BirdLogProxy(bird);
        BirdTimeProxy birdTimeProxy = new BirdTimeProxy(birdLogProxy);

        birdTimeProxy.fly();*/
        /*Bird bird = new Bird();
        ClassLoader classLoader = bird.getClass().getClassLoader();
        Class<?>[] interfaces = bird.getClass().getInterfaces();
        System.out.println("jiekou:"+interfaces);
        BirdProxy birdProxy = new BirdProxy(bird);
        Flyable proxyInstance = (Flyable)Proxy.newProxyInstance(classLoader, interfaces, birdProxy);
        proxyInstance.fly();*/
        MapperProxy proxy = new MapperProxy();

        UserMapper mapper = proxy.newInstance(UserMapper.class);
        User user = mapper.getUserById(1001);

        System.out.println("ID:" + user.getId());
        System.out.println("Name:" + user.getName());
        System.out.println("Age:" + user.getAge());

        System.out.println(mapper.toString());

    }
}
