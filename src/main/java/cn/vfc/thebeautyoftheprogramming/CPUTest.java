package cn.vfc.thebeautyoftheprogramming;

public class CPUTest {
    public static void main(String[] args) {
        for(;;){
            for (int i=0; i<9600000; i++){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
