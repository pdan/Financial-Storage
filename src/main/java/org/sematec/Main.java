package org.sematec;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Runnable task1 = new Runnable(){
            @Override
            public void run(){
                System.out.println(Thread.currentThread().getName() + " is running");
            }
        };

        Runnable task2  = new Runnable(){
            @Override
            public void run(){
                System.out.println(Thread.currentThread().getName() + " is running");
            }
        };

        Runnable task3  = new Runnable(){
            @Override
            public void run(){
                System.out.println(Thread.currentThread().getName() + " is running");
            }
        };

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run(){
                System.out.println(Thread.currentThread().getName() + " is running");
            }
        });



        Thread thread1 = new Thread(task1);
        thread1.start();
        thread1.setPriority(2);
        thread2.start();

        new Thread(task3).start();

    }
}
