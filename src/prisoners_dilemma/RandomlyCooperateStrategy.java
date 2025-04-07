package prisoners_dilemma;

public class RandomlyCooperateStrategy extends Strategy{
    public RandomlyCooperateStrategy(Prisoner owner){
        super(owner);
    }

    @Override
    public boolean doICooperate(){
        return mvc.Utilities.rng.nextBoolean();
    }
}
