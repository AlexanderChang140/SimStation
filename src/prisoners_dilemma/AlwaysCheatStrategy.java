package prisoners_dilemma;

public class AlwaysCheatStrategy extends Strategy{
    public AlwaysCheatStrategy(Prisoner owner) {
        super(owner);
    }

    @Override
    public boolean doICooperate(){
        return false;
    }
}
