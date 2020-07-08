package cn.vfc.decorator;

public class HouseBlend extends Beverage {

    public HouseBlend(){
        desrcription = "HouseBlend";
    }

    @Override
    public double cost() {
        return 3.6;
    }
}
