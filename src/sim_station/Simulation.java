package sim_station;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import mvc.*;
import sim_station.agent.Agent;
import sim_station.agent.AgentState;
import sim_station.agent.ObserverAgent;
import tools.Utilities;

public class Simulation extends Model {
    private static final long serialVersionUID = 1L;
    public static Integer WORLD_SIZE = 250; // height & width of the world (& view)
    private final ObserverAgent observer = new ObserverAgent(this);
    protected ArrayList<Agent> agents;
    protected int alive;
    protected int clock;

    @Deprecated
    public Simulation(String name) {
        clock = 0;
        agents = new ArrayList<>();
    }

    public Simulation() {
        clock = 0;
        agents = new ArrayList<>();
    }

    public synchronized void start() {
        agents.clear();
        populate();
        agents.forEach(Agent::start);
        observer.start();
        changed();
        clock = 0;
    }

    public synchronized void pause() {
        if (agents.get(0).getState() == AgentState.STOPPED) {
            Utilities.error("Model is stopped!");
            return;
        }
        agents.forEach(Agent::pause);
        observer.pause();
        changed();
    }

    public synchronized void resume() {
        if (agents.get(0).getState() == AgentState.STOPPED) {
            Utilities.error("Model is stopped!");
            return;
        }
        agents.forEach(Agent::resume);
        observer.resume();
        changed();
    }

    public synchronized void stop() {
        agents.forEach(Agent::stop);
        observer.stop();
        changed();
    }

    public String[] getStats() {
        String[] stats = new String[3];
        stats[0] = "# agents = " + agents.size();
        stats[1] = "clock = " + clock;
        stats[2] = "# alive " + alive;
        return stats;
    }

    public void updateStatistics() {
        alive = (int) agents.stream().filter(n -> n.getState() != AgentState.STOPPED).count();
        clock++;
    }

    public synchronized Agent getNeighbor(Agent thisAgent) {
        Agent nextAgent = null;
        int thisX = thisAgent.getX();
        int thisY = thisAgent.getY();

        for (int i = 0; i < agents.size(); i++) {
            Agent a = agents.get(i);
            if (i == 0) {
                nextAgent = a;
                continue;
            }

            double diffAX = Math.abs(a.getX() - thisX);
            double diffAY = Math.abs(a.getY() - thisY);
            double distAtThis = Math.sqrt((diffAX * diffAX) + (diffAY * diffAY));

            double diffNearX = Math.abs(nextAgent.getX() - thisX);
            double diffNearY = Math.abs(nextAgent.getY() - thisY);
            double distNearThis = Math.sqrt((diffNearX * diffNearX) + (diffNearY * diffNearY));

            if (distAtThis < distNearThis) {
                nextAgent = a;
            }
        }
        return nextAgent;
    }

    public synchronized Agent getNeighbor(Agent asker, double radius) {
        Agent neighbor = null;
        boolean found = false;
        int i = Utilities.rng.nextInt(agents.size());
        int start = i;
        while (!found) {
            Agent candidate = agents.get(i);

            if (candidate != asker && asker.getDistance(candidate) < radius) {
                neighbor = agents.get(i);
                found = true;
            } else {
                i = (i + 1) % agents.size();
                if (i == start)
                    break;
            }
        }
        return neighbor;

    }

    public void populate() {
        agents.clear();
    }

    public ArrayList<Agent> getAgents() {
        return agents;
    }
}
