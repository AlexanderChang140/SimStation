package prisoners_dilemma.strategy;

import prisoners_dilemma.Prisoner;
import prisoners_dilemma.strategy.Strategy;

public class CopyLastStrategy extends Strategy {
    public CopyLastStrategy(Prisoner owner){
        super(owner);
    }

    @Override
    public boolean doICooperate(){
        return owner.getLastOpponent();
    }
}
