package plague;

import sim_station.*;

public class PlaguePanel extends SimStationPanel{

    public PlaguePanel(SimFactory factory) {
        super(factory);
    }

    public static void main (String[] args) {
        SimFactory factory = new PlagueFactory();
        SimStationPanel panel = new SimStationPanel(factory);
        panel.display();
    }
}
