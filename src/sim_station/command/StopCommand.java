package sim_station.command;

import tools.Command;
import mvc.Model;
import sim_station.Simulation;

public class StopCommand extends Command {
    public StopCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() {
        Simulation sim = (Simulation) model;
        sim.stop();
    }
}
