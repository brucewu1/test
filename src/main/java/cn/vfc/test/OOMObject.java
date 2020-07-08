package cn.vfc.test;

import java.util.ArrayList;

public class OOMObject {
    public byte[]placeholder=new byte[64*1024];

    public static void fillHeap(int num) throws Exception{
        ArrayList<OOMObject> oomObjects = new ArrayList<>();
        for (int i = 0;i < num;i++){
            Thread.sleep(50);
            oomObjects.add(new OOMObject());
        }

    }


    public static void main(String[] args) throws Exception{
        fillHeap(1000);
        System.gc();
    }
}
