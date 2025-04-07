package sim_station;

import mvc.Command;
import mvc.Model;

public class PauseCommand extends Command {
    public PauseCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() {
        Simulation sim = (Simulation) model;
        sim.pause();
    }
}