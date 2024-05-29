public class Sword implements Equipment{
    private int x;
    private int y;
    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public boolean build(int x, int y, Field field) {
        return false;
    }

    @Override
    public boolean destroy(int x, int y, Field field) {
        return false;
    }

    @Override
    public void sendCoor(int x, int y, Unit unit) {
        if(x == unit.getX() && y == unit.getY()){
            unit.isHaveSword(true);
        }
    }
}
