package org.disk.autoracing.car;

import org.disk.autoracing.AutoRacingApp;
import org.disk.autoracing.race.Race;

import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private int speed;
    private Race race;
    private String name;
    private static int CARS_COUNT;
    private static final CyclicBarrier CB = new CyclicBarrier(AutoRacingApp.CARS_COUNT);

    static {
        CARS_COUNT=0;
    }

    public Car(Race race) {
        this.speed = (int) (80 + Math.random() * 10);
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
        }
    }

}
