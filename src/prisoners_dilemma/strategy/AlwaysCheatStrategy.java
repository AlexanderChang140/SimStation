package prisoners_dilemma.strategy;

import prisoners_dilemma.Prisoner;
import prisoners_dilemma.strategy.Strategy;

public class AlwaysCheatStrategy extends Strategy {
    public AlwaysCheatStrategy(Prisoner owner) {
        super(owner);
    }

    @Override
    public boolean doICooperate(){
        return false;
    }
}
