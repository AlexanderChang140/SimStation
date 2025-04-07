package plague;

import sim_station.Simulation;

public class PlagueSimulation extends Simulation {
    public static int VIRULENCE = 50; //Percent of infection
    public static int RESISTANCE = 2; //Percent of resisting infection
    public static int POP_SIZE = 50;

    public PlagueSimulation(String name) {
        super(name);
    }

    public void populate() {
        for (int i = 0; i < POP_SIZE; i++) {
            agents.add(new Plague(this));
        }
    }

    @Override
    public String[] getStats() {
        double count = agents.stream()
                .filter(agent -> ((Plague) agent).isInfected())
                .count();

        String[] stats = new String[3];
        stats[0] = "# agents = " + agents.size();
        stats[1] = "Clock = " + clock;
        stats[2] = "% infected: " + Math.round(count / POP_SIZE * 100) + "%";
        return stats;
    }
}
