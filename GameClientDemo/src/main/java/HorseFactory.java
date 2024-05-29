public class HorseFactory extends UnitFactory{
    @Override
    public Unit create(int x, int y) {
        return new Horse(x,y);
    }
}
