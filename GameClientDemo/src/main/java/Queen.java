import java.util.ArrayList;

public class Queen extends Unit{
    public Queen(int x, int y) {
        super(x, y);
    }

    @Override
    public void setTarget(int x, int y) {
       setPath(new WaveAlg().findQueenPath(getX(), getY(), x, y, getField().getCloneMap()));
    }
}
