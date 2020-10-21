package org.disk.autoracing;

import org.disk.autoracing.car.Car;
import org.disk.autoracing.race.Race;
import org.disk.autoracing.stage.Road;
import org.disk.autoracing.stage.Tunnel;

import java.util.concurrent.CyclicBarrier;

public class AutoRacingApp {
    public static final int CARS_COUNT = 10;
    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(CARS_COUNT);
        Race race = new Race(new Road(1000), new Tunnel(500), new Road(750));
        Car[] cars = new Car[CARS_COUNT];

        for (int i = 0; i < CARS_COUNT; i++) {
            cars[i] = new Car(race);
        }

        for (int i = 0; i < CARS_COUNT; i++) {
            new Thread(cars[i]).start();
        }

    }
}
