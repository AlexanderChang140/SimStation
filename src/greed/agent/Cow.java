package greed.agent;

import greed.GreedSimulation;
import sim_station.agent.Heading;
import sim_station.agent.MobileAgent;
import sim_station.Simulation;
import tools.Mth;

import javax.swing.*;

public class Cow extends MobileAgent {
    private final int greediness;
    private int energy = 100;

    public Cow(Simulation sim , int greediness) {
        super(sim);
        this.greediness = greediness;
    }

    @Override
    public void update() {
        if (energy == 0) {
            return;
        }

        GreedSimulation greedSimulation = (GreedSimulation) world;
        Patch patch = greedSimulation.getPatch(xc, yc);

        if (patch == null) {
            return;
        }
        patch.consume(this, greediness);
        world.changed();
    }

    public boolean doMove() {
        GreedSimulation greedSimulation = (GreedSimulation) world;

        if (energy >= greedSimulation.getMoveEnergy()) {
            setEnergy(energy - greedSimulation.getMoveEnergy());
            heading = Heading.randomHeading();
            move(greedSimulation.getPatchSize() + 5);
            return true;
        }
        return false;
    }

    public int getGreediness() {
        return greediness;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = Mth.clamp(energy, 0, 100);
        if (this.energy == 0) {
            stop();
            System.out.println("stop");
        }
    }
}
