package sim_station;

import mvc.*;
import sim_station.agent.Agent;


import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class SimStationView extends View {
    public static int BOX_X_CORNER = 0;
    public static int BOX_Y_CORNER = 0;
    public static int LINE_THICKNESS = 3;
    public SimStationView(Model model) {
        super(model);
        setSize(Simulation.WORLD_SIZE,Simulation.WORLD_SIZE);

    }
    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);

        Graphics2D g2 = (Graphics2D) gc;
        Color oldColor = gc.getColor();

        Simulation sim = (Simulation)model;
        ArrayList<Agent> agents = sim.getAgents();

        for (Agent a : agents) {
            drawAgent(g2, a);
        }

        drawBorder(g2);
        g2.setColor(oldColor);
    }

    protected void drawAgent(Graphics2D graphics2D, Agent agent) {
        graphics2D.setColor(Color.RED);
        graphics2D.fillRect(agent.getX(), agent.getY(), Agent.AGENT_SIZE, Agent.AGENT_SIZE);
    }

    protected void drawBorder(Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLACK);
        graphics2D.setStroke(new BasicStroke(2));
        graphics2D.draw(new Rectangle2D.Double(BOX_X_CORNER, BOX_Y_CORNER, Simulation.WORLD_SIZE + Agent.AGENT_SIZE, Simulation.WORLD_SIZE + Agent.AGENT_SIZE));
    }
}
