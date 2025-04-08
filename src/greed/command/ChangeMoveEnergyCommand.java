package greed.command;

import greed.GreedSimulation;
import mvc.Model;
import tools.Command;

public class ChangeMoveEnergyCommand extends Command {
    private final int moveEnergy;

    public ChangeMoveEnergyCommand(Model model, int moveEnergy) {
        super(model);
        this.moveEnergy = moveEnergy;
    }

    @Override
    public void execute() {
        GreedSimulation greedSimulation = (GreedSimulation) model;
        greedSimulation.setMoveEnergy(moveEnergy);
    }
}
