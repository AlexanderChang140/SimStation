package plague.command;

import mvc.Model;
import plague.PlagueSimulation;
import tools.Command;

public class ChangeInitialInfectedCommand extends Command {
    private final int initialInfected;

    public ChangeInitialInfectedCommand(Model model, int initialInfected) {
        super(model);
        this.initialInfected = initialInfected;
    }

    @Override
    public void execute() {
        PlagueSimulation plagueSimulation = (PlagueSimulation) model;
        plagueSimulation.setInitialInfected(initialInfected);
    }
}
