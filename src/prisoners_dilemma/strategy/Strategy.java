package prisoners_dilemma.strategy;

import prisoners_dilemma.Prisoner;

public abstract class Strategy {
    protected Prisoner owner;
    public Strategy(Prisoner owner) {
        this.owner = owner;
    }
    public abstract boolean doICooperate();
}
