package greed;

import greed.agent.Cow;
import greed.agent.Patch;
import mvc.Model;
import sim_station.Simulation;
import sim_station.agent.Agent;
import sim_station.SimStationView;
import tools.Mth;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class GreedView extends SimStationView {
    public GreedView(Model model) {
        super(model);
    }

    @Override
    protected void drawAgent(Graphics2D graphics2D, Agent agent) {
        GreedSimulation greedSimulation = (GreedSimulation) model;

        if (agent instanceof Patch patch) {
            graphics2D.setColor(getShadedGreen(patch.getEnergy()));
            graphics2D.fillRect(agent.getX() + 1, agent.getY() + 1, greedSimulation.getPatchSize() - 1, greedSimulation.getPatchSize() - 1);
        }
        else if (agent instanceof Cow cow && cow.getEnergy() == 0) {
            graphics2D.setColor(Color.WHITE);
            graphics2D.fillRect(agent.getX(), agent.getY(), Agent.AGENT_SIZE, Agent.AGENT_SIZE);
        }
        else {
            super.drawAgent(graphics2D, agent);
        }
    }

    @Override
    protected void drawBorder(Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLACK);
        graphics2D.setStroke(new BasicStroke(2));
        graphics2D.draw(new Rectangle2D.Double(BOX_X_CORNER + 1, BOX_Y_CORNER + 1, Simulation.WORLD_SIZE + Agent.AGENT_SIZE + 5, Simulation.WORLD_SIZE + Agent.AGENT_SIZE + 5));

    }

    public Color getShadedGreen(int value) {
        value = Math.max(0, Math.min(value, 100));
        double scale = (double) value / 100;
        int min = 100;
        int max = 200;
        int added = (int) ((max - min) * scale);
        int brightness = Mth.clamp(max - added, min, max);

        return new Color(brightness, 255, brightness);
    }
}
