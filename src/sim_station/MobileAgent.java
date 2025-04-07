package sim_station;

public abstract class MobileAgent extends Agent {
    protected Heading heading = Heading.randomHeading();

    public MobileAgent(Simulation sim) {
        super(sim);
    }

    public void move(int steps) {
        if(heading == Heading.NORTH) {
            yc = yc - steps;
        }
        else if(heading == Heading.SOUTH) {
            yc = yc + steps;
        }
        else if(heading == Heading.EAST) {
            xc = xc + steps;
        }
        else {	//West
            xc = xc - steps;
        }
        outOfBoundsAdapter();
    }

}
