package org.disk.autoracing.stage;

import org.disk.autoracing.car.Car;

public abstract class Stage {
    protected int length;
    protected String description;

    public String getDescription() {
        return description;
    }

    public abstract void go (Car car);
}
