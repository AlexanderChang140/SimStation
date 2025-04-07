package prisoners_dilemma;

import prisoners_dilemma.strategy.Strategy;
import sim_station.agent.Heading;
import tools.Utilities;
import sim_station.agent.MobileAgent;

public class Prisoner extends MobileAgent {
    private final static int BOTH_COOPERATES = 3;
    private final static int BOTH_CHEATS = 1;
    private final static int I_COOPERATE_OTHER_CHEATS = 0;
    private final static int I_CHEAT_OTHER_COOPERATES = 5;

    private int fitness = 0;
    protected Strategy strategy;
    private boolean lastOpponent;

    private int speed = 0;
    public final int MAX_SPEED = 5;

    public Prisoner(PrisonerSimulation ps) {
        super(ps);
        lastOpponent = Utilities.rng.nextBoolean();
    }
    public boolean getLastOpponent() {
        return lastOpponent;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public int getFitness() {
        return fitness;
    }

    @Override
    public void update() {
        Prisoner neighbor = (Prisoner) world.getNeighbor(this);
        boolean iCooperated = strategy.doICooperate();
        boolean neighborCooperated = neighbor.strategy.doICooperate();

        // Contains all four possible outcomes and increments fitness accordingly
        if(iCooperated) {
            if (neighborCooperated) {   //We both cooperated
                this.fitness += BOTH_COOPERATES;
                neighbor.fitness += BOTH_COOPERATES;
            } else {                                  //Only neighbor cheated
                this.fitness += I_COOPERATE_OTHER_CHEATS;
                neighbor.fitness += I_CHEAT_OTHER_COOPERATES;
            }
        } else {    //I'm cheating
            if(neighborCooperated) {    //Only neighbor cooperated
                this.fitness += I_CHEAT_OTHER_COOPERATES;
                neighbor.fitness += I_COOPERATE_OTHER_CHEATS;
            } else {    //We both cheated
                this.fitness += BOTH_CHEATS;
                neighbor.fitness += BOTH_CHEATS;
            }
        }

        heading = Heading.randomHeading();
        speed = Utilities.rng.nextInt(MAX_SPEED);
        move(speed);
        world.changed();
    }
}
