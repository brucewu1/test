package cn.vfc.mode.proxy;

public class BirdLogProxy implements Flyable {
    private Flyable flyable;

    public BirdLogProxy(Flyable flyable) {
        this.flyable = flyable;
    }

    @Override
    public void fly() {
        System.out.println("bird fly start ...");
        flyable.fly();
        System.out.println("bird fly end ...");
    }
}
