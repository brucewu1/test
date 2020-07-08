package cn.vfc.lock;

public class WhatReentrant {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(this){
                    System.out.println("第一次获取锁 这个锁是" + this);
                }
                int index = 1;
                while (true){
                    synchronized (this){
                        System.out.println("第"+(++index)+"次获取锁，这个锁是" + this);
                    }
                    if(index == 10){
                        break;
                    }
                }
            }
        }).start();
    }
}
