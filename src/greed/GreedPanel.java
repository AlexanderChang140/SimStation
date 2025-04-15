package greed;

import greed.command.ChangeGreedCommand;
import greed.command.ChangeGrowthCommand;
import greed.command.ChangeMoveEnergyCommand;
import sim_station.SimFactory;
import sim_station.SimStationPanel;
import tools.CommandProcessor;

import javax.swing.*;
import java.awt.*;

public class GreedPanel extends SimStationPanel {
    public GreedPanel(SimFactory simFactory) {
        super(simFactory);

        JPanel components = new JPanel();
        components.setLayout(new GridLayout(3, 1));

        JPanel greed = new JPanel();
        JLabel greedLabel = new JLabel("Greed");
        greed.add(greedLabel);
        JSlider greedSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);
        greedSlider.setMajorTickSpacing(10);
        greedSlider.setPaintTicks(true);
        greedSlider.setPaintLabels(true);
        greedSlider.addChangeListener(e -> CommandProcessor.execute(new ChangeGreedCommand(model, greedSlider.getValue())));
        greed.add(greedSlider);
        components.add(greed);

        JPanel growth = new JPanel();
        JLabel growthLabel = new JLabel("Growth");
        growth.add(growthLabel);
        JSlider growthSlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
        growthSlider.setMinorTickSpacing(2);
        growthSlider.setMajorTickSpacing(10);
        growthSlider.setPaintTicks(true);
        growthSlider.setPaintLabels(true);
        growthSlider.addChangeListener(e -> CommandProcessor.execute(new ChangeGrowthCommand(model, growthSlider.getValue())));
        growth.add(growthSlider);
        components.add(growth);

        JPanel moveEnergy = new JPanel();
        JLabel moveEnergyLabel = new JLabel("Move Energy");
        moveEnergy.add(moveEnergyLabel);
        JSlider moveSlider = new JSlider(JSlider.HORIZONTAL, 0, 50, 10);
        moveSlider.setMajorTickSpacing(10);
        moveSlider.setPaintTicks(true);
        moveSlider.setPaintLabels(true);
        moveSlider.addChangeListener(e -> CommandProcessor.execute(new ChangeMoveEnergyCommand(model, moveSlider.getValue())));
        moveEnergy.add(moveSlider);
        components.add(moveEnergy);

        panel.add(components);
    }

    public static void main(String[] args) {
        GreedPanel panel = new GreedPanel(new GreedFactory());
        panel.display();
    }
}
