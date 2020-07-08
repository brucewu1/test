package cn.vfc.test;

public class Apple {
    @FruitProvider(id = 1,name = "XX公司",address = "深圳通大厦")
    private String appleProvider;

    @FruitProvider(id = 2,name = "vfc",address = "hunna")
    private String bananaPrivider;

    public String getAppleProvider() {
        return appleProvider;
    }

    public void setAppleProvider(String appleProvider) {
        this.appleProvider = appleProvider;
    }

    public static void main(String[] args) {
        FruitInfoUtil.getFruitInfo(Apple.class);
    }
}
