package tools;

import mvc.Model;

public abstract class Command {
    protected Model model;

    public Command(Model model) {
        super();
        this.model = model;
    }

    public abstract void execute();
}

