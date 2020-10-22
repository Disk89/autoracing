package org.disk.autoracing;

import org.disk.autoracing.car.Car;
import org.disk.autoracing.race.Race;
import org.disk.autoracing.stage.Road;
import org.disk.autoracing.stage.Tunnel;

public class AutoRacingApp {
    public static final int CARS_COUNT = 10;
    public static void main(String[] args) {
        Race race = new Race(new Road(1000), new Tunnel(500), new Road(750));
        Car[] cars = new Car[CARS_COUNT];

        for (int i = 0; i < CARS_COUNT; i++) {
            cars[i] = new Car(race);
        }

        for (int i = 0; i < CARS_COUNT; i++) {
            new Thread(cars[i]).start();
        }

        try {
            Car.countDownLatch.await();
            System.err.println("RACING is OVER!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
