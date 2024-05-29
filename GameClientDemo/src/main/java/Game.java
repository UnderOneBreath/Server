import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.awt.event.WindowEvent;
import java.util.Collections;

public class Game extends JFrame implements Runnable{
    BufferedImage img;
    BufferedImage leyka;
    BufferedImage flower;
    BufferedImage sword;
    SpriteSheet sheet;
    private int x=0;
    private int y=0;
    int x1 = 50;
    int y1 = 50;
    int col = 12;
    int row = 10;
    int left = 50;
    int top = 50;
    private boolean select = false;
    ArrayList<Unit> units = new ArrayList<>();
    private boolean isWork = true;
    Field field = new Field();
    ArrayList<Equipment> Shovel = new ArrayList<>();
    ArrayList<Equipment> Leyka = new ArrayList<>();
    ArrayList<Sword> swords = new ArrayList<>();
    private static ArrayList<Integer> cou = new ArrayList<>();

    public static void main(String[] args) {
        Game frame = new Game();
        frame.setBounds(10, 10,800,600);
        frame.setDefaultCloseOperation(2);
        frame.setVisible(true);
        frame.setLayout(new FlowLayout());
        for (int i = 0; i < 3; i++) {
            cou.add(i);
        }
    }

    public int getCou(){
        Collections.rotate(cou,1);
        return cou.get(0);
    }
    public Game() throws HeadlessException {
        try {
            img = ImageIO.read(new File("shovel.png"));
            leyka = ImageIO.read(new File("waterdropper.png"));
            flower = ImageIO.read(new File("flower.png"));
            sword = ImageIO.read(new File("sword.png"));
        }catch (IOException ex){
            throw new RuntimeException(ex);
        }
        new Thread(this).start();
        units.add(new RookFactory().create(2,3));
        units.add(new QueenFactory().create(1,1));
        units.add(new ElephantFactory().create(5,6));
        units.add(new HorseFactory().create(7,2));
        for(Unit u: units){
            u.setField(field);
        }
        sheet = new SpriteSheet("Dragon.png");
        Shovel shovel = new Shovel();
        shovel.setX(1);
        shovel.setY(2);
        Shovel shovel1 = new Shovel();
        shovel1.setX(4);
        shovel1.setY(8);
        Leyka leyka = new Leyka();
        leyka.setX(2);
        leyka.setY(6);
        Shovel.add(shovel);
//        Shovel.add(shovel1);
        Leyka.add(leyka);
        Sword sword = new Sword();
        sword.setX(7);
        sword.setY(6);
        swords.add(sword);
//        addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//                System.out.println("@@"+e.getKeyChar()+","+e.getKeyCode());
//                if(e.getKeyChar() == 'b' || e.getKeyChar() == 'и'){
//                    for (int i = 0; i < units.size(); i++) {
//                        Unit u = units.get(i);
//                        if(u.isSelected() && u.getShovel()) {
//                            u.build();
//                        }
//                    }
//                }else if(e.getKeyChar() == 'd' || e.getKeyChar() == 'в'){
//                    for (int i = 0; i < units.size(); i++) {
//                        Unit u = units.get(i);
//                        if(u.isSelected() && u.getShovel()) {
//                            u.destroy();
//                        }
//                    }
//                }else if(e.getKeyChar() == 'g' || e.getKeyChar() == 'п'){
//                    for (int i = 0; i < units.size(); i++) {
//                        Unit u = units.get(i);
//                        if (u.isSelected() && u.getEq()) {
//                            u.dropEquipment();
//                        }
//                    }
//                }
//                else if(e.getKeyChar() == 'p' || e.getKeyChar() == 'з'){
//                    for (int i = 0; i < units.size(); i++) {
//                        Unit u = units.get(i);
//                        if(u.isSelected() && u.getLeyka()) {
//                            u.buildFlower();
//                        }
//                    }
//                }
//                else if(e.getKeyChar() == 'f' || e.getKeyChar() == 'а'){
//                    for (int i = 0; i < units.size(); i++) {
//                        Unit u = units.get(i);
//                        if(u.isSelected() && u.getSword()) {
//                            if(field.isUnit(u.getX(),u.getY()-1)){
//                                for (int j = 0; j < units.size(); j++) {
//                                    Unit u_h = units.get(j);
//                                    if(u.getX() == u_h.getX() && u.getY() - 1 == u_h.getY()){
//                                        u.hit(u_h);
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
////                System.out.println("##"+e.getKeyChar()+","+e.getKeyCode());
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//
//            }
//        });
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                x = (e.getX() - left)/x1;
                y = (e.getY() - top)/y1;
                if(e.getButton() == MouseEvent.BUTTON1){
                    for (int i = 0; i < units.size(); i++) {
                        Unit u = units.get(i);
                        if(x == u.getX() && y == u.getY()){
                            u.setSelected(!u.isSelected());
                        }
                    }
                }else{
                    for (int i = 0; i < units.size(); i++) {
                        Unit u = units.get(i);
                        if(u.isSelected() && y >= 0 && x >= 0 && x <= 11 && y <= 9) {
                            u.setTarget(x, y);
                        }
                    }
                }
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                isWork = false;
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        this.createBufferStrategy(2);
        BufferStrategy bs = this.getBufferStrategy();
        g = bs.getDrawGraphics();
        super.paint(g);
        drawUnit(g);
        drawGrid(g);
        drawWall(g);
        drawShovel(g);
        drawLeyka(g);
        drawFlower(g);
        drawSword(g);
        bs.show();
    }
    private void drawSword(Graphics g){
        for(Equipment e: swords){
            int cx = e.getX() * x1 + left;
            int cy = e.getY() * y1 + top;
            g.drawImage(sword, cx, cy, x1, y1,null);
        }
    }
    private void drawLeyka(Graphics g) {
        for(Equipment e: Leyka){
            int cx = e.getX() * x1 + left;
            int cy = e.getY() * y1 + top;
            g.drawImage(leyka, cx, cy, x1, y1,null);
        }
    }

    private void drawShovel(Graphics g) {
        for(Equipment e: Shovel){
            int cx = e.getX() * x1 + left;
            int cy = e.getY() * y1 + top;
            g.drawImage(img, cx, cy, x1, y1,null);
        }
    }

    private void drawGrid(Graphics g) {
        //12/10
        g.setColor(Color.BLUE);
        for (int i = 0; i < col+1; i++) {
            g.drawLine(left + x1 * i, top, left + x1 * i, top + y1 * row);
        }
        g.setColor(Color.BLUE);
        for (int i = 0; i < row+1; i++) {
            g.drawLine(left, top + y1 * i, left + x1 * col, top + y1 * i);
        }
    }

    private void drawWall(Graphics g) {
        int [][] map = field.getMap();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j] == 1) {
                    g.setColor(Color.BLUE);
                    g.fillRect(left + j*x1,top + i * y1, x1, y1);
                }
            }
        }
    }
    private void drawFlower(Graphics g) {
        int [][] map = field.getMap();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j] == 3) {
                    g.drawImage(flower, left + j*x1, top + i * y1, x1, y1,null);
                }
            }
        }
    }
    private void drawUnit(Graphics g){
        for (int i = 0; i < units.size(); i++) {
            Unit u = units.get(i);
            int cx = u.getX() * x1 + left;
            int cy = u.getY() * y1 + top;
            g.drawImage(sheet.getImg(u.getCourse(),getCou()), cx, cy, x1, y1,null);
//            g.setColor(Color.YELLOW);
//            g.fillOval(cx, cy, 50, 50);
            g.setColor(Color.BLACK);
            g.drawString(u.getClass().getSimpleName(), cx + x1/5, cy + y1/2);
            g.setColor(Color.BLUE);
            g.fillRect(cx,cy + y1/15,50,10);
            g.setColor(Color.GREEN);
            g.fillRect(cx,cy + y1/15,u.getHp()/2,10);
            if (u.isSelected()) {
                g.setColor(Color.BLUE);
                g.drawOval(cx, cy, 50, 50);
            }
            if(u.getShovel()){
                g.drawImage(img, cx, cy, x1, y1,null);
            }
            if(u.getLeyka()){
                g.drawImage(leyka, cx, cy, x1, y1,null);
            }
            if(u.getSword()){
                g.drawImage(sword, cx, cy, x1, y1,null);
            }
        }
    }
    @Override
    public void run() {
        while(isWork) {
            for(Equipment e: Shovel){
                for(Unit u: units) {
                    if(!u.getEq())
                        e.sendCoor(e.getX(), e.getY(),u);
                }
            }
            for(Equipment e: Leyka){
                for(Unit u: units) {
                    if(!u.getEq())
                        e.sendCoor(e.getX(), e.getY(),u);
                }
            }
            for(Equipment e: swords){
                for(Unit u: units) {
                    if(!u.getEq())
                        e.sendCoor(e.getX(), e.getY(),u);
                }
            }
            for (Unit u: units) {
                u.move();
            }
//            repaint();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void getUnits() {
        try {
            String json = IOUtils.toString(new URL("http://localhost:8080/units"), StandardCharsets.UTF_8);
            System.out.println(json);
            units.clear();
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                System.out.println("#"+array.get(i));
                JSONObject obj = array.getJSONObject(i);
                System.out.println(obj.getString("surname"));
                System.out.println(obj.getInt("x"));
                System.out.println(obj.getInt("y"));
                units.add(new Unit(obj.getInt("x"),obj.getInt("y")));
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
