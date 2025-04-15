package plague;

import sim_station.*;
import sim_station.agent.Heading;
import sim_station.agent.MobileAgent;
import tools.Utilities;

import java.util.Random;

public class Plague extends MobileAgent {
    private static final double RADIUS = 20;

    private final int speed;
    private final boolean resistant;
    private final int infectionChance;
    private final int outcomeTime;
    private int currTime = 0;
    private boolean infected;


    public Plague(Simulation sim, int infectionChance, int outcomeTime) {
        super(sim);

        int luck = Utilities.rng.nextInt(100);
        resistant = luck < PlagueSimulation.RESISTANCE;
        this.infectionChance = infectionChance;
        this.outcomeTime = outcomeTime;
        heading = Heading.randomHeading();
        speed = Utilities.rng.nextInt(5);
    }

    @Override
    public void update() {
        Plague neighbor = (Plague) world.getNeighbor(this, RADIUS);
        currTime++;

        if (infected && currTime >= outcomeTime) {
            doOutcome();
            currTime = 0;
        }

        if (neighbor != null) {
            if (this.isInfected() && !neighbor.isResistant()) {
                neighbor.infected = true;
            }
            else if (!this.isResistant() && neighbor.isInfected()) {
                Random rand = new Random();
                int random = rand.nextInt(100);
                if (random < infectionChance) {
                    this.infected = true;
                }
            }
        }
        heading = Heading.randomHeading();
        move(speed);
        world.changed();
    }

    private void doOutcome() {

    }

    public boolean isInfected() {
        return infected;
    }

    public void setInfected(boolean isInfected) {
        this.infected = isInfected;
    }

    public boolean isResistant() {
        return resistant;
    }
}