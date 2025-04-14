package greed;

import mvc.Model;
import mvc.View;
import sim_station.SimStationFactory;

public class GreedFactory extends SimStationFactory {
    @Override
    public Model makeModel() {
        return new GreedSimulation("Greed Simulation");
    }

    @Override
    public View getView(Model model) {
        return new GreedView(model);
    }

    @Override
    public String getTitle() {
        return "Greed";
    }
}
