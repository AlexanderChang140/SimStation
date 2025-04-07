package prisoners_dilemma;

public class AlwaysCooperateStrategy extends Strategy {
    public AlwaysCooperateStrategy(Prisoner owner) {
        super(owner);
    }

    @Override
    public boolean doICooperate(){
        return true;
    }
}
