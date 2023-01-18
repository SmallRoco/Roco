package UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Fighting extends JPanel {


    //"F:\\洛克王国资源\\大寒\\sprites\\DefineSprite_88\\frames.gif"
    @Override
    public void paint(Graphics g) {
        Toolkit.getDefaultToolkit().createImage("F:\\洛克王国资源\\大寒\\idle.gif");
        System.out.println(101);
        g.setColor(new Color(0,0,0));
        g.fillRect(0,0,this.getWidth(),this.getWidth());
        g.drawImage(Toolkit.getDefaultToolkit().createImage("F:\\洛克王国资源\\大寒\\sprites\\DefineSprite_88\\frames.gif"),0,0,null);
    }
}
