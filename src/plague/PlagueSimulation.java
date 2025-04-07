package plague;

import sim_station.*;

public class PlagueSimulation extends Simulation {
    public static int VIRULENCE = 50; //Percent of infection
    public static int RESISTANCE = 2; //Percent of resisting infection
    public static int POP_SIZE = 50;

    public PlagueSimulation (String name){
        super(name);
    }

    public void populate(){
        for (int i = 0; i < POP_SIZE; i++){
            agents.add(new Plague(this));
        }
    }

    public String[] getStats(){
        double count = 0;
        for (Agent a: agents){
            Plague p = (Plague)a;
            if(p.isInfected()){
                count++;
            }
        }

        String[] stats = new String[3];
        stats[0] = "# agents = " + agents.size();
        stats[1] = "Clock = " + clock;
        stats[2] = "% if infected: " + Math.round(count/POP_SIZE * 100) + "%";
        return stats;
    }
}
