public class ElephantFactory extends UnitFactory{
    @Override
    public Unit create(int x, int y) {
        return new Elephant(x,y);
    }
}
