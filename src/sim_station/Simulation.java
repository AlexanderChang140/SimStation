package sim_station;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import mvc.*;
import sim_station.agent.Agent;
import sim_station.agent.AgentState;
import tools.Utilities;

public class Simulation extends Model {
    private static final long serialVersionUID = 1L;
    public static Integer WORLD_SIZE = 250; // height & width of the world (& view)
    protected ArrayList<Agent> agents;
    private Timer timer;
    protected int clock;
    private String name;


    public Simulation(String name) {
        this.name = name;
        timer = new Timer();
        clock = 0;
        agents = new ArrayList<Agent>();
    }

    public synchronized void start() {
        agents.clear();
        populate();
        restartTimer();
        for(Agent a : agents)
            a.start();
        changed();
    }

    public synchronized void pause() {
        if(agents.get(0).getState() == AgentState.STOPPED) {
            Utilities.error("Model is stopped!");
            return;
        }
        stopTimer();
        for(Agent a : agents)
            a.pause();
        changed();
    }

    public synchronized void resume() {
        if(agents.get(0).getState() == AgentState.STOPPED) {
            Utilities.error("Model is stopped!");
            return;
        }
        startTimer();
        for(Agent a : agents)
            a.resume();
        changed();
    }

    public synchronized void stop() {
        stopTimer();
        for(Agent a : agents) {
            a.stop();
        }
        changed();
    }
    public String[] getStats() {
        String[] stats = new String[2];
        stats[0] = "# agents = " + agents.size();
        stats[1] = "clock = " + clock;
        return stats;
    }

    public synchronized Agent getNeighbor(Agent thisAgent) {
        Agent nextAgent = null;
        int thisX = thisAgent.getX(); int thisY = thisAgent.getY();

        for(int i = 0; i < agents.size(); i++) {
            Agent a = agents.get(i);
            if(i == 0) {
                nextAgent = a;
                continue;
            }

            double diffAX = Math.abs(a.getX() - thisX);
            double diffAY = Math.abs(a.getY() - thisY);
            double distAtThis = Math.sqrt((diffAX * diffAX) + (diffAY * diffAY));

            double diffNearX = Math.abs(nextAgent.getX() - thisX);
            double diffNearY = Math.abs(nextAgent.getY() - thisY);
            double distNearThis = Math.sqrt((diffNearX * diffNearX) + (diffNearY * diffNearY));

            if(distAtThis < distNearThis) {
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
        while(!found) {
            Agent candidate = agents.get(i);

            if(candidate != asker && asker.getDistance(candidate) < radius) {
                neighbor = agents.get(i);
                found = true;
            } else {
                i = (i+1) % agents.size();
                if(i == start)
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

    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new ClockUpdater(), 1000, 1000);
    }

    private void stopTimer() {
        timer.cancel();
        timer.purge();
    }

    private void restartTimer() {
        timer.cancel();
        timer.purge();
        clock = 0;
        startTimer();
    }
    private class ClockUpdater extends TimerTask {
        public void run() {
            clock++;
        }
    }
}
