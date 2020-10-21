package org.disk.autoracing.car;

import org.disk.autoracing.AutoRacingApp;
import org.disk.autoracing.race.Race;

import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int COUNT;
    private int speed;
    private Race race;
    private String name;
    private static int CARS_COUNT;
    private static final CyclicBarrier CB = new CyclicBarrier(10);

    static {
        CARS_COUNT=0;
        COUNT = AutoRacingApp.CARS_COUNT;
    }

    public Car(Race race) {
        this.speed = (int) (20 + Math.random() * 10);
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
            System.out.println( this .name + " готовится" );
            Thread.sleep( 500 + ( int )(Math.random() * 800 ));
            System.out.println( this .name + " готов" );
            CB.await();

        } catch (Exception e) {
            e.printStackTrace();
        }
        for ( int i = 0 ; i < race.getStages().size(); i++) {
            race.getStages().get(i).go( this );
        }
    }

}
