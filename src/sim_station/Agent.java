package sim_station;

import java.io.Serializable;
import mvc.Utilities;

public abstract class Agent implements Runnable, Serializable {
    private static final long serialVersionUID = 1L;
    public static Integer AGENT_SIZE = 5; // size of each agent
    private String name;		//name of the agent
    protected Heading heading;	//Agent heading
    private AgentState state;	//state
    private Thread thread;
    private int xc; 	//x coordinate
    private int yc;		//y coordinate
    protected Simulation world;

    public Agent(Simulation sim) {
        heading = Heading.randomHeading();	//make random
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

    public void move(int steps) {
        if(heading == Heading.NORTH) {
            yc = yc - steps;
        }
        else if(heading == Heading.SOUTH) {
            yc = yc + steps;
        }
        else if(heading == Heading.EAST) {
            xc = xc + steps;
        }
        else {	//West
            xc = xc - steps;
        }
        outOfBoundsAdapter();
    }

    private void outOfBoundsAdapter() {
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
