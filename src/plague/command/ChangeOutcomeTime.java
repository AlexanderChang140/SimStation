package plague.command;

import mvc.Model;
import plague.PlagueSimulation;
import tools.Command;

public class ChangeOutcomeTime extends Command {
    private final int outcomeTime;

    public ChangeOutcomeTime(Model model, int outcomeTime) {
        super(model);
        this.outcomeTime = outcomeTime;
    }

    @Override
    public void execute() {
        PlagueSimulation plagueSimulation = (PlagueSimulation) model;
        plagueSimulation.setOutcomeTime(outcomeTime);
    }
}
