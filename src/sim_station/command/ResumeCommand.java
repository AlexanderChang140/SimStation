package sim_station.command;

import tools.Command;
import mvc.Model;
import sim_station.Simulation;

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
