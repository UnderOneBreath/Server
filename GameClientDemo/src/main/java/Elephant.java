import java.util.ArrayList;

public class Elephant extends Unit{
    public Elephant(int x, int y) {
        super(x, y);
    }

    @Override
    public void setTarget(int x, int y) {
        setPath(new WaveAlg().findElephantPath(getX(), getY(), x, y, getField().getCloneMap()));
    }
}
