import java.util.ArrayList;
import java.util.Collections;
import java.text.CollationElementIterator;

import static java.lang.Math.sqrt;
public abstract class Unit {
    private int x;
    private int y;
    private boolean isSelected = false;
    private Field field;
    private int hp = 100;
    private ArrayList<Point> path = new ArrayList<>();
    private Equipment equipment = new Shovel();
    private Equipment leyka = new Leyka();
    private boolean isHaveShovel = false;
    private boolean isHaveLeyka = false;
    private boolean isHaveEq = false;
    private boolean isHaveSword = false;
    public boolean getEq(){
        return isHaveEq;
    }
    public void isHaveShovel(boolean t) {
            isHaveShovel = t;
            isHaveEq = t;
    }
    public boolean getShovel(){
        return isHaveShovel;
    }
    public void isHaveLeyka(boolean t) {
        isHaveLeyka = t;
        isHaveEq = t;
    }
    public boolean getLeyka(){
        return isHaveLeyka;
    }
    public ArrayList<Point> getPath() {
        return path;
    }

    public void setPath(ArrayList<Point> path) {
        this.path = path;
    }

    public void setField(Field field) {
        this.field = field;
        field.putItem(x,y);
    }
    public Unit(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public abstract void setTarget(int x, int y);


    public Field getField() {
        return field;
    }


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void move(){
        moveWaveAlg(path);
    }
    public void moveWaveAlg(ArrayList<Point> path){
        if(!path.isEmpty()){
            Point p = path.getFirst();
            int[][] map = field.getMap();
            if(field.isFree(p.getX(),p.getY())){
                getField().remove(getX(), getY());
                if (p.getY()<getY() && p.getX()==getX()){
                    courses =0;
                } else if (p.getY()<getY() && p.getX()>getX()){
                    courses =1;
                } else if (p.getY()==getY() && p.getX()>getX()){
                    courses =2;
                }else if (p.getY()>getY() && p.getX()>getX()){
                    courses =3;
                }else if (p.getY()>getY() && p.getX()==getX()){
                    courses =4;
                }else if (p.getY()>getY() && p.getX()<getX()){
                    courses =5;
                }else if (p.getY()==getY() && p.getX()<getX()){
                    courses =6;
                }else if (p.getY() <getY() && p.getX()<getX()){
                    courses =7;
                }
                setXD(getX()-p.getX());
                setYD(getY()-p.getY());
                setX(p.getX());
                setY(p.getY());
                getField().putItem(getX(), getY());
                path.removeFirst();
            }
        }
    }
    public int getHp(){
        return hp;
    }
    public void setHp(int hp){
        this.hp = hp;
    }
    public void changeHP(){
        hp -= 2;
    }

    public void build() {
        if(y-1 >= 0)
            equipment.build(x, y, field);
    }

    public void destroy() {
        if(y-1 >= 0)
            equipment.destroy(x, y, field);
    }
    public void buildFlower(){
        if(y-1 >= 0)
            leyka.build(x, y, field);
    }
    public void dropEquipment() {
        isHaveShovel = false;
        isHaveLeyka = false;
        isHaveSword = false;
        isHaveEq = false;
    }

    public int getCourse() {
        return courses;
    }
    private int XD = 0;
    private int YD = 0;
    public void setXD(int x){
        this.XD = x;
    }
    public void setYD(int y){
        this.YD = y;
    }
    private int courses;
    public boolean getSword(){
        return isHaveSword;
    }
    public void isHaveSword(boolean t){
        isHaveSword = t;
        isHaveEq = t;
    }

    public void hit(Unit uH) {
        uH.setHp(uH.getHp() - 20);
    }
}
