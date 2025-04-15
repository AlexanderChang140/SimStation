package plague.command;

import mvc.Model;
import plague.PlagueSimulation;
import tools.Command;

public class ChangeInitialPopulationCommand extends Command {
    private final int initialPopulation;

    public ChangeInitialPopulationCommand(Model model, int initialPopulation) {
        super(model);
        this.initialPopulation = initialPopulation;
    }

    @Override
    public void execute() {
        PlagueSimulation plagueSimulation = (PlagueSimulation) model;
        plagueSimulation.setInitialPopulation(initialPopulation);
    }
}
