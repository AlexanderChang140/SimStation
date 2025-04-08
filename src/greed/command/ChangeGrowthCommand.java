package greed.command;

import greed.GreedSimulation;
import mvc.Model;
import tools.Command;

public class ChangeGrowthCommand extends Command {
    private final int growth;

    public ChangeGrowthCommand(Model model, int growth) {
        super(model);
        this.growth = growth;
    }

    @Override
    public void execute() {
        GreedSimulation greedSimulation = (GreedSimulation) model;
        greedSimulation.setGrowthRate(growth);
    }
}
