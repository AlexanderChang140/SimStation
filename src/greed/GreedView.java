package greed;

import greed.agent.Cow;
import greed.agent.Patch;
import mvc.Model;
import sim_station.Agent;
import sim_station.SimStationView;
import tools.Mth;

import java.awt.*;

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
