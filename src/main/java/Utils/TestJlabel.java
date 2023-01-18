package Utils;

import javax.swing.*;
import java.awt.*;

public class TestJlabel extends JLabel {


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(255,255,255));

        System.out.println(getX()+","+getY());
        for (int i = 0; i <5; i++) {
            g.drawRect(i,i,getWidth()-1-i,getHeight()-1-i);
        }
    }
}
