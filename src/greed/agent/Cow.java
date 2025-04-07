package greed.agent;

import greed.GreedSimulation;
import sim_station.agent.Heading;
import sim_station.agent.MobileAgent;
import sim_station.Simulation;
import tools.Mth;

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

        if (energy + greediness > 100 || patch.consume(this, greediness)) {
            setEnergy(energy - greedSimulation.getWaitPenalty());
        }
        else {
            setEnergy(energy - greedSimulation.getMoveEnergy());
            heading = Heading.randomHeading();
            move(greedSimulation.getPatchSize() + 5);
        }
        world.changed();
    }

    public int getGreediness() {
        return greediness;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = Mth.clamp(energy, 0, 100);
    }
}
