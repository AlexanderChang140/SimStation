package plague;

import sim_station.*;

public class PlaguePanel extends SimStationPanel{
    public PlaguePanel(SimFactory factory) {
        super(factory);
    }

    public static void main(String[] args) {
        PlaguePanel panel = new PlaguePanel(new PlagueFactory());
        panel.display();
    }
}
