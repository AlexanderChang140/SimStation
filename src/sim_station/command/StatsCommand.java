package sim_station.command;

import tools.Command;
import mvc.Model;
import tools.Utilities;
import sim_station.Simulation;

public class StatsCommand extends Command {
    public StatsCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() {
        Simulation sim = (Simulation) model;
        Utilities.inform(sim.getStats());
    }
}
