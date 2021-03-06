package org.disk.autoracing.stage;

import org.disk.autoracing.AutoRacingApp;
import org.disk.autoracing.car.Car;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    Semaphore smp = new Semaphore(AutoRacingApp.CARS_COUNT/2) ;
    public static CountDownLatch countDownLatch = new CountDownLatch(AutoRacingApp.CARS_COUNT);
    public Tunnel(int length) {
        this.length = length;
        this.description = ("Tunnel " + length + " metres");
    }


    @Override
    public void go(Car car) {
        try {
            System.out.println(car.getName() + " getting ready (wait) the stage: " + description);
            smp.acquire();
            System.out.println(car.getName() + " started the stage: " + description);
            Thread.sleep(length/car.getSpeed() * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.err.println(car.getName() + " completed the stage: " + description);
            smp.release();

        }
    }
}
