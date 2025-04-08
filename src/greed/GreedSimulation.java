package greed;

import greed.agent.Cow;
import greed.agent.Patch;
import sim_station.Simulation;
import tools.Mth;

public class GreedSimulation extends Simulation {
    private Patch[][] patches;
    private int growthRate = 1;
    private int greediness = 10;
    private int waitPenalty = 5;
    private int moveEnergy = 10;
    private int numCows = 50;
    private int patchSize = 10;
    private int dim = (WORLD_SIZE / patchSize) + 1;

    public GreedSimulation(String name) {
        super(name);
    }

    @Override
    public void populate() {
        patches = new Patch[dim][dim];

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                Patch patch = new Patch(this, growthRate, i * patchSize, j * patchSize);
                agents.add(patch);
                patches[i][j] = patch;
            }
        }

        for (int i = 0; i < numCows; i++) {
            agents.add(new Cow(this, greediness));
        }
    }

    public void setGrowthRate(int growthRate) {
        this.growthRate = growthRate;
    }

    public void setGreediness(int greediness) {
        this.greediness = greediness;
    }

    public int getWaitPenalty() {
        return waitPenalty;
    }

    public int getMoveEnergy() {
        return moveEnergy;
    }

    public void setMoveEnergy(int moveEnergy) {
        this.moveEnergy = moveEnergy;
    }

    public Patch getPatch(int xc, int yc) {
        int x = Mth.clamp(xc / 10, 0, dim - 1);
        int y = Mth.clamp(yc / 10, 0, dim - 1);
        return patches[x][y];
    }

    public int getDim() {
        return dim;
    }

    public int getPatchSize() {
        return patchSize;
    }
}
