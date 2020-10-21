package org.disk.autoracing.race;

import org.disk.autoracing.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;

public class Race {
    ArrayList<Stage> stages;

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    public ArrayList<Stage> getStages() {
        return stages;
    }
}
