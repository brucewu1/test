package cn.vfc.decorator;

public abstract class Beverage {
    String desrcription = "unknown";
    public String getDescription(){
        return desrcription;
    }
    public abstract double cost();
}
