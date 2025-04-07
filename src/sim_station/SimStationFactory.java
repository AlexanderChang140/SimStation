package sim_station;

import mvc.*;
import sim_station.command.*;
import tools.Command;

public class SimStationFactory implements SimFactory {

    @Override
    public Model makeModel()
    {
        return new Simulation("test");
    }

    @Override
    public String[] getEditCommands()
    {
        return new String[] {"Start", "Pause", "Resume", "Stop", "Stats"};
    }

    @Override
    public Command makeEditCommand(Model model, String type)
    {
        if (type.equals("Start"))
            return new StartCommand(model);
        else if (type.equals("Pause"))
            return new PauseCommand(model);
        else if (type.equals("Resume"))
            return new ResumeCommand(model);
        else if (type.equals("Stop"))
            return new StopCommand(model);
        else if (type.equals("Stats"))
            return new StatsCommand(model);

        return null;
    }

    @Override
    public String getTitle()
    {
        return "sim_station";
    }

    public String[] getHelp()
    {
        String[] cmmds = new String[5];
        cmmds[0] = "Start: Starts the model";
        cmmds[1] = "Pause: Pauses the model";
        cmmds[2] = "Resume: Resumes the model";
        cmmds[3] = "Stop: Stops the model";
        cmmds[4] = "Stats: Shows the model statistics";
        return cmmds;
    }

    @Override
    public String about()
    {
        return "Group 1, 2025. All rights reserved.";
    }



    @Override
    public View getView(Model model) {
        // TODO Auto-generated method stub
        return new SimStationView(model);
    }
}
