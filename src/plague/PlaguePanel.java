package plague;

import plague.command.ChangeInfectionChanceCommand;
import plague.command.ChangeInitialInfectedCommand;
import plague.command.ChangeInitialPopulationCommand;
import plague.command.ChangeOutcomeTime;
import sim_station.*;
import tools.CommandProcessor;

import javax.swing.*;
import java.awt.*;

public class PlaguePanel extends SimStationPanel {
    public PlaguePanel(SimFactory factory) {
        super(factory);

        JPanel components = new JPanel();
        components.setLayout(new GridLayout(4, 1));

        JPanel initialInfected = new JPanel();
        JLabel initialInfectedLabel = new JLabel("Initial % infected");
        initialInfected.add(initialInfectedLabel);
        JSlider initialInfectedSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 5);
        initialInfectedSlider.setMajorTickSpacing(10);
        initialInfectedSlider.setPaintTicks(true);
        initialInfectedSlider.setPaintLabels(true);
        initialInfectedSlider.addChangeListener(e -> CommandProcessor.execute(new ChangeInitialInfectedCommand(model, initialInfectedSlider.getValue())));
        initialInfected.add(initialInfectedSlider);
        components.add(initialInfected);

        JPanel infectionChance = new JPanel();
        JLabel infectionChanceLabel = new JLabel("Infection Chance");
        infectionChance.add(infectionChanceLabel);
        JSlider infectionChanceSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        infectionChanceSlider.setMajorTickSpacing(10);
        infectionChanceSlider.setPaintTicks(true);
        infectionChanceSlider.setPaintLabels(true);
        infectionChanceSlider.addChangeListener(e -> CommandProcessor.execute(new ChangeInfectionChanceCommand(model, infectionChanceSlider.getValue())));
        infectionChance.add(infectionChanceSlider);
        components.add(infectionChance);

        JPanel population = new JPanel();
        JLabel populationLabel = new JLabel("Initial Population");
        population.add(populationLabel);
        JSlider populationSlider = new JSlider(JSlider.HORIZONTAL, 0, 200, 50);
        populationSlider.setMajorTickSpacing(50);
        populationSlider.setPaintTicks(true);
        populationSlider.setPaintLabels(true);
        populationSlider.addChangeListener(e -> CommandProcessor.execute(new ChangeInitialPopulationCommand(model, populationSlider.getValue())));
        population.add(populationSlider);
        components.add(population);

        JPanel outcomeTime = new JPanel();
        JLabel outcomeTimeLabel = new JLabel("Outcome Time");
        outcomeTime.add(outcomeTimeLabel);
        JSlider outcomeTimeSlider = new JSlider(JSlider.HORIZONTAL, 0, 500, 200);
        outcomeTimeSlider.setMajorTickSpacing(100);
        outcomeTimeSlider.setPaintTicks(true);
        outcomeTimeSlider.setPaintLabels(true);
        outcomeTimeSlider.addChangeListener(e -> CommandProcessor.execute(new ChangeOutcomeTime(model, outcomeTimeSlider.getValue())));
        outcomeTime.add(outcomeTimeSlider);
        components.add(outcomeTime);

        panel.add(components);
    }

    public static void main(String[] args) {
        PlaguePanel panel = new PlaguePanel(new PlagueFactory());
        panel.display();
    }
}
