package prisoners_dilemma.strategy;

import prisoners_dilemma.Prisoner;
import tools.Utilities;

public class RandomlyCooperateStrategy extends Strategy{
    public RandomlyCooperateStrategy(Prisoner owner){
        super(owner);
    }

    @Override
    public boolean doICooperate(){
        return Utilities.rng.nextBoolean();
    }
}
