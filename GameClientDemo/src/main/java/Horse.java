import java.util.ArrayList;

public class Horse extends Unit{
    public Horse(int x, int y) {
        super(x, y);
    }
    @Override
    public void setTarget(int x, int y) {
        setPath(new WaveAlg().findHorsePath(getX(), getY(), x, y, getField().getCloneMap()));
    }
}
