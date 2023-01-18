package UI;

import Config.ImageUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

public class AnimalLabel extends JLabel {

    static boolean drawComplete = true;

    public volatile boolean att2 = false;



    @Override
    public void paint(Graphics g) {

        drawComplete = false;

        if(att2){
            super.paint(g);
        }else {

            //创建一块画布
            BufferedImage bi = (BufferedImage) createImage(getWidth(), getHeight());
            //bi = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
            Graphics big = bi.createGraphics();

            //让父类负责渲染
            super.paint(big);

            //System.out.println((getWidth() - bi.getMinX());
            //Raster data = bi.getData(new Rectangle(bi.getMinX(), bi.getMinY(), getWidth() - bi.getMinX(), getHeight() - bi.getMinY()));

            long l = System.currentTimeMillis();
            //对的到的图片进行显示


            int w = getWidth();
            int h = getHeight();
            for (int i = bi.getMinX(); i < w; i++) {
                for (int j = bi.getMinY(); j < h; j++) {
                    int rgb = 0;
                    if ((rgb = bi.getRGB(i, j)) != -1118482) {
                        g.setColor(new Color(rgb));
                        g.drawLine(getWidth() - 1 - i, j, getWidth() - 1 - i, j);
                    } else j += 1;
                }
            }

            //System.out.println(System.currentTimeMillis()-l);
        }

        drawComplete = true;
    }

    @Override
    public void setIcon(Icon icon) {
        while (!drawComplete) {
            try {
                //System.out.println(123);
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        super.setIcon(icon);



    }
}
