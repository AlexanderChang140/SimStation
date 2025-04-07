package plague;

import mvc.Model;
import sim_station.Agent;
import sim_station.SimStationView;
import sim_station.Simulation;

import java.awt.*;

public class PlagueView extends SimStationView {
    public PlagueView(Model model) {
        super(model);
        setSize(Simulation.WORLD_SIZE,Simulation.WORLD_SIZE);
    }

    @Override
    protected void drawAgent(Graphics2D graphics2D, Agent agent) {
        Plague plague = (Plague) agent;
        if (plague.isInfected()) {
            graphics2D.setColor(Color.RED);
        }
        else {
            graphics2D.setColor(Color.GREEN);
        }
        graphics2D.fillRect(plague.getX(), plague.getY(), Agent.AGENT_SIZE, Agent.AGENT_SIZE);
    }
}
