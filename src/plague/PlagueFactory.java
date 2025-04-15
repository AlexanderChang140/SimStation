package plague;

import sim_station.*;
import mvc.*;

public class PlagueFactory extends SimStationFactory {
    public PlagueFactory() {
        super();
    }

    @Override
    public Model makeModel() {
        return new PlagueSimulation("Plague Simulation");
    }

    @Override
    public View getView (Model model) {
        return new PlagueView(model);
    }

    @Override
    public String getTitle() {
        return "Plague";
    }
}
