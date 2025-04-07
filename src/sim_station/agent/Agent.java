package sim_station.agent;

import java.io.Serializable;

import sim_station.SimStationView;
import sim_station.Simulation;
import tools.Utilities;

public abstract class Agent implements Runnable, Serializable {
    private static final long serialVersionUID = 1L;
    public static Integer AGENT_SIZE = 5; // size of each agent

    private AgentState state;	//state
    private Thread thread;
    protected int xc; 	//x coordinate
    protected int yc;		//y coordinate
    protected Simulation world;

    public Agent(Simulation sim) {	//make random
        state = null;
        xc = Utilities.rng.nextInt(Simulation.WORLD_SIZE + 1);
        yc = Utilities.rng.nextInt(Simulation.WORLD_SIZE + 1);
        thread = new Thread();
        world = sim;
    }

    public void run() {
        thread = Thread.currentThread();
        while(state != AgentState.STOPPED) {
            state = AgentState.RUNNING;
            update();
            world.changed();
            try {
                Thread.sleep(50);
                synchronized(this){
                    while(state == AgentState.PAUSED) {
                        wait();
                    }
                }
            }catch(InterruptedException e){
                System.err.println(e);
            }
        }
    }

    public synchronized void start() {
        thread = new Thread(this);
        state = AgentState.READY;
        thread.start();
    }

    public synchronized void pause() {
        state = AgentState.PAUSED;
    }

    public synchronized void resume() {
        if(state != AgentState.STOPPED) {
            state = AgentState.READY;
            notify();
        }
    }

    public synchronized void stop() {
        state = AgentState.STOPPED;
    }

    public abstract void update();

    protected void outOfBoundsAdapter() {
        if(yc > Simulation.WORLD_SIZE + SimStationView.BOX_Y_CORNER ) {	//If agent hits south border
            yc = yc - Simulation.WORLD_SIZE;
        }
        else if(yc < SimStationView.BOX_Y_CORNER ) {		//If agent hits north border
            yc = yc + Simulation.WORLD_SIZE;
        }
        else if(xc > Simulation.WORLD_SIZE + SimStationView.BOX_X_CORNER ) {		//If agent hits east border
            xc = xc - Simulation.WORLD_SIZE;
        }
        else if(xc < SimStationView.BOX_X_CORNER) {		//If agent hits west border
            xc = xc + Simulation.WORLD_SIZE;
        }
    }

    public void join() throws InterruptedException {
        if(thread != null) {
            thread.join();
        }
    }
    public AgentState getState() {
        return state;
    }
    public int getX() {
        return xc;
    }
    public int getY() {
        return yc;
    }

    public double getDistance(Agent a) {
        return Math.sqrt(Math.pow(this.xc - a.xc,2) + Math.pow(this.yc - a.yc , 2));
    }
}
