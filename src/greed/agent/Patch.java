package greed.agent;

import sim_station.Agent;
import sim_station.Simulation;
import tools.Mth;

public class Patch extends Agent {
    private final int growthRate;

    private int energy = 100;
    private boolean isReady = true;

    public Patch(Simulation sim, int growthRate, int xc, int yc) {
        super(sim);
        this.xc = xc;
        this.yc = yc;
        this.growthRate = growthRate;
    }

    @Override
    public void update() {
        isReady = true;
        setEnergy(energy + growthRate);
        world.changed();
    }

    public boolean consume(Cow cow, int amount) {
        if (amount <= energy && isReady) {
            cow.setEnergy(cow.getEnergy() + amount);
            setEnergy(energy - cow.getGreediness());
            isReady = false;
            return true;
        }
        return false;
    }

    public int getEnergy() {
        return energy;
    }

    private void setEnergy(int energy) {
        this.energy = Mth.clamp(energy, 0, 100);
    }
}
