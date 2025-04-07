package sim_station;

import mvc.Command;
import mvc.Model;

public class ResumeCommand extends Command {
    public ResumeCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() {
        Simulation sim = (Simulation) model;
        sim.resume();
    }
}
