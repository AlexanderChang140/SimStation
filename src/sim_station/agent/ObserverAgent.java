package sim_station.agent;

import sim_station.Simulation;

public class ObserverAgent extends Agent {
    public ObserverAgent(Simulation sim) {
        super(sim);
    }

    @Override
    public void update() {
        world.updateStatistics();
    }
}
