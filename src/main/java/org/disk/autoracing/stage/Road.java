package org.disk.autoracing.stage;

import org.disk.autoracing.car.Car;

public class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = ("Road " + length + " metres");
    }


    @Override
    public void go(Car car) {
        try {
            System.out.println(car.getName() + " started the stage: " + description);
            Thread.sleep(length/car.getSpeed() * 1000);
            System.out.println(car.getName() + " completed the stage: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
