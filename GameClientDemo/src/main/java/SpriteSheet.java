import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class SpriteSheet extends JFrame {
    private int x1 = 90;
    private int y1 = 85;
    BufferedImage img;

    public static void main(String[] args) {
        SpriteSheet sheet = new SpriteSheet("Dragon.png");
        sheet.setDefaultCloseOperation(2);
        sheet.setBounds(10,10,600,200);
        sheet.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                g.drawImage(this.getImg(i, j),50 + 90 * i,100 + 90 * j,null);
            }
        }
    }

    public SpriteSheet(String imagepath){
        try{
            img = ImageIO.read(new File(imagepath));
        } catch (IOException ex){
            throw new RuntimeException(ex);
        }
    }
    public Image getImg(int i, int j){
        BufferedImage frame = this.img;
        if(i > 4) {
            frame = this.img.getSubimage(0 + x1 * (8 - i), y1 * j,x1, y1);
            BufferedImage empty = new BufferedImage(x1, y1,2);
            Graphics g = empty.createGraphics();
            g.drawImage(frame, x1,0,0, x1, 0, 0, x1, x1,(ImageObserver) null);
            frame = empty;
//            return img.getSubimage(x1 * course, 0, x1, y1);
        }else {
            frame = this.img.getSubimage(0 + x1 * i,y1 * j, x1, y1);
        }
        return frame;
    }
}
