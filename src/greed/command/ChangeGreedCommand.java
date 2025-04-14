package greed.command;

import greed.GreedSimulation;
import mvc.Model;
import tools.Command;

public class ChangeGreedCommand extends Command {
    private final int greediness;

    public ChangeGreedCommand(Model model, int greediness) {
        super(model);
        this.greediness = greediness;
    }

    @Override
    public void execute() {
        GreedSimulation greedSimulation = (GreedSimulation) model;
        greedSimulation.setGreediness(greediness);
    }
}
