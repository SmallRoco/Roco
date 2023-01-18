package UI;

import Config.AllConfig;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BloodLine extends JLabel {

    int allHp;
    int hp;
    //是否是左面的血条
    boolean left;
    boolean first = true;

    BufferedImage bl01 ;
    BufferedImage bl00 ;

    public BloodLine(){
        try {
            bl01 =  ImageIO.read(new File(AllConfig.rootPath+"UI/血条/01.PNG"));
            bl00 =  ImageIO.read(new File(AllConfig.rootPath+"UI/血条/00.PNG"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getAllHp() {
        return allHp;
    }

    public void setAllHp(int allHp,int hp) {
        this.allHp = allHp;
        this.hp = hp;
        repaint();
    }



    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }


    public void change(int value){


        while (value>hp&&hp<allHp){
            hp++;
            MainFrame.sleep(1);
            repaint();
        }
        while (value<hp){
            hp--;
            MainFrame.sleep(1);
            repaint();
        }
        if(hp==0){
            if(left){
                MainFrame.pawnRun(MainFrame.pet1,"dead");
            }else {
                MainFrame.pawnRun(MainFrame.pet2,"dead");
            }
        }
    }



    @Override
    public void paint(Graphics g) {

        if(allHp==0)return;



            g.drawImage(bl01,0,0,null);//g.fillRect(0,0,getWidth(),getHeight());


            int ed = getWidth()*hp/allHp;
            int st = getWidth()-ed;

            if(left){
                st=0;
            }else {
                ed = getWidth();
            }
            /*if(allHp!=hp){
                System.out.println(0);
            }*/

            /*g.setColor(new Color(255, 246, 0));
            g.fillRect(st,0,ed,getHeight());*/


            for (int i = st; i <ed; i++) {
                for (int j = 0; j <getHeight(); j++) {
                    g.setColor(new Color(bl00.getRGB(i,j)));
                    g.drawLine(i, j, i, j);
                }
            }


            g.setColor(new Color(255,255,255));
            g.setFont(new Font("宋体",Font.ITALIC,30));	//这句代码要放在前面---------------------------------------------


            FontMetrics fontMetrics = g.getFontMetrics();
            String s = hp+"/"+allHp;
            //获取对应文字在该字体下对应的宽度和高度
            int stringWidth=fontMetrics.stringWidth(s);
            int stringAscent=fontMetrics.getAscent();

            int xo=getWidth()/2-stringWidth/2;
            int yo=getHeight()/2+stringAscent/2;
            g.drawString(s, xo, yo-3);
            g.drawString(s, xo-1, yo-3);
            g.drawString(s, xo-2, yo-3);


    }
}
