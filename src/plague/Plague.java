package plague;

import sim_station.*;
import sim_station.agent.Heading;
import sim_station.agent.MobileAgent;
import tools.Utilities;

public class Plague extends MobileAgent {

    private static final double RADIUS = 20;

    private boolean infected;
    private boolean resistant;
    private int speed;


    public Plague(Simulation sim) {
        super(sim);

        int luck = Utilities.rng.nextInt(100);
        resistant = luck < PlagueSimulation.RESISTANCE;
        heading = Heading.randomHeading();
        speed = Utilities.rng.nextInt(5);
        infected();
    }


    private void infected() {
        if(!resistant) {
            int luck = Utilities.rng.nextInt(100);
            this.infected = (luck < PlagueSimulation.VIRULENCE);
        }
    }

    @Override
    public void update() {
        Plague neighbor = (Plague) world.getNeighbor(this, RADIUS);
        if (neighbor != null ) {
            if(this.isInfected() && !neighbor.isResistant()) {
                neighbor.infected = true;
            } else if (!this.isResistant() && neighbor.isInfected()) {
                this.infected = true;
            }
        }
        heading = Heading.randomHeading();
        move(speed);
        world.changed();
    }

    public boolean isInfected() {
        return infected;
    }

    public boolean isResistant() {
        return resistant;
    }
}