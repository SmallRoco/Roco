package UI;

import Config.AllConfig;
import Config.ImageUtils;
import content.Pet;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class IconLabel extends JLabel {


    Pet pet;

    boolean left;

    BufferedImage border;

    public IconLabel() {





    }

    public void setIconLabel(Pet pet,boolean left){
        this.pet = pet;
        this.left = left;
        try {
            if(left){
                border = ImageIO.read(new File(AllConfig.rootPath+"UI/头像/001.PNG"));
            }else border = ImageIO.read(new File(AllConfig.rootPath+"UI/头像/000.PNG"));
        }catch (IOException e){
            e.printStackTrace();
        }
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.setFont(new Font("宋体",Font.BOLD,12));
        g.setColor(new Color(255,255,255));
        //画头像框
        g.drawImage(border,0,0,null);





        if(!left) {
            //画头像
            g.drawImage(ImageUtils.getPetIcon(pet.getName()),28,0,null);
            //画属性图标
            g.drawImage(ImageUtils.getTypeSmall(pet.getType()),11,49,null);
            //画名字
            g.drawString(pet.getName(), 25, 83);
            //画等级
            g.setFont(new Font("宋体", Font.BOLD, 10));
            g.drawString("100", 80, 83);
        }else {

            g.drawImage(ImageUtils.flipHorizontally(ImageUtils.getPetIcon(pet.getName())),0,0,null);
            //画属性图标
            g.drawImage(ImageUtils.getTypeSmall(pet.getType()),66,49,null);

            g.drawString(pet.getName(), 25, 83);
            //画等级
            g.setFont(new Font("宋体", Font.BOLD, 10));
            g.drawString("100", 1, 83);
        }


    }
}
