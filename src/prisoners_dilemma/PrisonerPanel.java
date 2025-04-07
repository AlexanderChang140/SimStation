package prisoners_dilemma;

import sim_station.SimFactory;
import sim_station.SimStationPanel;

public class PrisonerPanel extends SimStationPanel {
    private static final long serialVersionUID = 1L;

    public PrisonerPanel(SimFactory factory) {
        super(factory);
    }

    public static void main(String[] args) {
        SimFactory factory = new PrisonerFactory();
        SimStationPanel panel = new SimStationPanel(factory);
        panel.display();
    }
}
