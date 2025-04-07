package sim_station;

import mvc.*;

public interface SimFactory extends AppFactory {
    public View getView(Model model);
}
