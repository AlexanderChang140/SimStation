package prisoners_dilemma.strategy;

import prisoners_dilemma.Prisoner;
import prisoners_dilemma.strategy.Strategy;

public class AlwaysCooperateStrategy extends Strategy {
    public AlwaysCooperateStrategy(Prisoner owner) {
        super(owner);
    }

    @Override
    public boolean doICooperate(){
        return true;
    }
}
