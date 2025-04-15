package plague.command;

import mvc.Model;
import plague.PlagueSimulation;
import tools.Command;

public class ChangeInfectionChanceCommand extends Command {
    private final int infectionChance;

    public ChangeInfectionChanceCommand(Model model, int infectionChance) {
        super(model);
        this.infectionChance = infectionChance;
    }

    @Override
    public void execute() {
        PlagueSimulation plagueSimulation = (PlagueSimulation) model;
        plagueSimulation.setInfectionChance(infectionChance);
    }
}
