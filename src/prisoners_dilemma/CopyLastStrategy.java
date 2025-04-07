package prisoners_dilemma;

public class CopyLastStrategy extends Strategy{
    public CopyLastStrategy(Prisoner owner){
        super(owner);
    }

    @Override
    public boolean doICooperate(){
        return owner.getLastOpponent();
    }
}
