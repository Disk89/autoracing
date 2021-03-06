package org.disk.autoracing.car;

import org.disk.autoracing.AutoRacingApp;
import org.disk.autoracing.race.Race;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Car implements Runnable {
    private int speed;
    private Race race;
    private String name;
    private static int CARS_COUNT;
    Lock lock = new ReentrantLock();
    public static CountDownLatch countDownLatch = new CountDownLatch(AutoRacingApp.CARS_COUNT);
    private static final CyclicBarrier CB = new CyclicBarrier(AutoRacingApp.CARS_COUNT, new Thread(()->{
        System.err.println("RACE STARTED!");
    }));

    static {
        CARS_COUNT=0;
    }

    public Car(Race race) {
        this.speed = (int) (100 + Math.random() * 10);
        this.race = race;
        CARS_COUNT++;
        name = "Car # " + CARS_COUNT;
    }

    public int getSpeed() {
        return speed;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        try {
            System.out.println( this .name + " getting ready" );
            Thread.sleep( 500 + ( int )(Math.random() * 800 ));
            System.out.println( this .name + " ready" );
            CB.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for ( int i = 0 ; i < race.getStages().size(); i++) {
            race.getStages().get(i).go( this );
            if(i == race.getStages().size() -1) {
                lock.lock();
                countDownLatch.countDown();
                if (countDownLatch.getCount() == AutoRacingApp.CARS_COUNT-1) {
                    System.err.println(this.getName() + " WIN!!!");
                }
                lock.unlock();
            }
        }
    }

}
