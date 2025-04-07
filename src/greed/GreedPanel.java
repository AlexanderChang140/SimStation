package greed;

import sim_station.SimFactory;
import sim_station.SimStationPanel;

public class GreedPanel extends SimStationPanel {
    public GreedPanel(SimFactory simFactory) {
        super(simFactory);
    }

    public static void main(String[] args) {
        GreedPanel panel = new GreedPanel(new GreedFactory());
        panel.display();
    }
}
