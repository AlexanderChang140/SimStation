package prisoners_dilemma;

import sim_station.SimStationFactory;
import mvc.Model;

public class PrisonerFactory extends SimStationFactory {
    public PrisonerFactory() {
        super();
    }

    @Override
    public Model makeModel(){
        return new PrisonerSimulation("Prisoner Simulation");
    }
}
