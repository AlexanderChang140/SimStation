package plague;

import sim_station.Simulation;

public class PlagueSimulation extends Simulation {
    public static int RESISTANCE = 2; //Percent of resisting infection
    private int infectionChance = 50; //Percent of infection
    private int initialInfected = 5;
    private int initialPopulation = 50;
    private int outcomeTime;

    public PlagueSimulation(String name) {
        super(name);
    }

    public void populate() {
        int infectedLeft = initialInfected;
        for (int i = 0; i < initialPopulation; i++) {
            Plague plague = new Plague(this, infectionChance, outcomeTime);
            agents.add(plague);
            if (infectedLeft > 0) {
                infectedLeft--;
                plague.setInfected(true);
            }
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
        stats[2] = "% infected: " + Math.round(count / initialPopulation * 100) + "%";
        return stats;
    }

    public void setInfectionChance(int infectionChance) {
        this.infectionChance = infectionChance;
    }

    public void setInitialInfected(int initialInfected) {
        this.initialInfected = initialInfected;
    }

    public void setInitialPopulation(int initialPopulation) {
        this.initialPopulation = initialPopulation;
    }

    public void setOutcomeTime(int outcomeTime) {
        this.outcomeTime = outcomeTime;
    }
}
