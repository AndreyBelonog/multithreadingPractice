package com.company;

import java.util.concurrent.atomic.AtomicInteger;

public class MultithreadingPractice {
    private AtomicInteger ourCounter = new AtomicInteger();

    public static void main(String[] args) {
        // hey1
	    MultithreadingPractice mp = new MultithreadingPractice();
	    mp.doWork();
    }

    public void increment(){
        ourCounter.getAndIncrement();
    }

    public void doWork(){
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++){
                increment();
            }
        });

        Thread thread2 = new Thread(() -> {
           for(int i = 0; i < 10000; i++){
               increment();
           }
        });

        thread1.start();
        thread2.start();

        try{
            thread1.join();
            thread2.join();
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }

        System.out.println(ourCounter);
    }
}
