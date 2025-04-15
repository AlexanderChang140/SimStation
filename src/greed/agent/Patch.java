package greed.agent;

import greed.GreedSimulation;
import sim_station.agent.Agent;
import sim_station.Simulation;
import tools.Mth;
import tools.Utilities;

import java.util.ArrayList;
import java.util.List;

public class Patch extends Agent {
    private final int growthRate;

    private int energy = 100;

    public Patch(Simulation sim, int growthRate, int xc, int yc) {
        super(sim);
        this.xc = xc;
        this.yc = yc;
        this.growthRate = growthRate;
    }

    @Override
    public void update() {
        grow();
        world.changed();
    }

    public synchronized void consume(Cow cow, int amount) {
        try {
            GreedSimulation greedSimulation = (GreedSimulation) world;

            if (amount <= energy) {
                eat(cow, amount);
            }
            else {
                if (!cow.doMove()) {
                    while (amount > energy && cow.getEnergy() > 0) {
                        wait();
                        cow.setEnergy(cow.getEnergy() - greedSimulation.getWaitPenalty());
                    }
                }
                if (amount <= energy && cow.getEnergy() > 0) {
                    eat(cow, amount);
                }
            }
        }
        catch (Exception e) {
            Utilities.error(e);
        }
    }

    private synchronized void grow() {
        setEnergy(energy + growthRate);
        notifyAll();
    }

    private synchronized void eat(Cow cow, int amount) {
        cow.setEnergy(cow.getEnergy() + amount);
        setEnergy(energy - cow.getGreediness());
    }

    public synchronized int getEnergy() {
        return energy;
    }

    private synchronized void setEnergy(int energy) {
        this.energy = Mth.clamp(energy, 0, 100);
    }
}
