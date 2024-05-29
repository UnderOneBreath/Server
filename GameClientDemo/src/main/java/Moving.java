import java.util.ArrayList;

public class Moving {
    public void move(ArrayList<Point> path, Field field, Unit unit){
        if(!path.isEmpty()){
            Point p = path.getFirst();
            unit.getField().remove(unit.getX(),unit.getY());
            unit.setX(p.getX());
            unit.setY(p.getY());
            unit.getField().putItem(unit.getX(),unit.getY());
            path.removeFirst();
        }
    }
}
