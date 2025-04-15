package sim_station;

import mvc.*;
import javax.swing.*;
import java.awt.*;

public class SimStationPanel extends AppPanel {
    public SimStationPanel(AppFactory factory) {
        super(factory);

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1, 5));

        Panel p = new Panel();
        JButton b = new JButton("Start");
        b.addActionListener(this);
        p.add(b);
        buttons.add(p);

        p = new Panel();
        b = new JButton("Pause");
        b.addActionListener(this);
        p.add(b);
        buttons.add(p);

        p = new Panel();
        b = new JButton("Resume");
        b.addActionListener(this);
        p.add(b);
        buttons.add(p);

        p = new Panel();
        b = new JButton("Stop");
        p.setLayout(new FlowLayout());
        b.addActionListener(this);
        p.add(b);
        buttons.add(p);

        p = new Panel();
        b = new JButton("Stats");
        p.setLayout(new FlowLayout());
        b.addActionListener(this);
        p.add(b);
        buttons.add(p);

        buttons.setPreferredSize(new Dimension(280, 100));

        panel.add(buttons, "North");
    }

    public static void main(String[] args) {
        AppPanel panel = new SimStationPanel(new SimStationFactory());
        panel.display();
    }

    @Override
    public void setModel(Model newModel) {
        super.setModel(newModel);
        Simulation simulation = (Simulation) newModel;
        simulation.resetThreads();
    }
}


