package game.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyButton extends JPanel {

    //按钮上显示的文本
    private String text;
    //按钮上文本的初始大小
    private int fontSize_s;
    //按钮上文本的焦点大小
    private int fontSize_e;
    //背景颜色
    private Color backColor;
    //字体颜色
    private Color fontColor;


    private boolean onFocus = false;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        repaint();
    }

    public MyButton(String text, int fontSize_s, int fontSize_e, Color backColor, Color fontColor) {
        this.text = text;
        this.fontSize_s = fontSize_s;
        this.fontSize_e = fontSize_e;
        this.backColor = backColor;
        this.fontColor = fontColor;

        this.setCursor(new Cursor(Cursor.HAND_CURSOR));

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

                onFocus = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                onFocus = false;
                repaint();
            }
        });
    }




    @Override
    public void paint(Graphics g) {


        g.setColor(backColor);
        g.fillRect(0,0,getWidth(),getHeight());
        g.setColor(fontColor);
        int fontSize = fontSize_s;
        if (onFocus) {
            g.drawLine(0,getHeight()-1,getWidth(),getHeight()-1);
            fontSize = fontSize_e;
        }


        g.drawLine(0,0,getHeight()/4,0);
        g.drawLine(0,0,0,getHeight()/4);
        g.drawLine(getWidth()-getHeight()/4,getHeight()-1,getWidth()-1,getHeight()-1);
        g.drawLine(getWidth()-1,getHeight()-getHeight()/4,getWidth()-1,getHeight()-1);

        g.setFont(new Font(Font.DIALOG,Font.TYPE1_FONT,fontSize));



        FontMetrics fontMetrics = g.getFontMetrics();
        int stringWidth=fontMetrics.stringWidth(text);
        int stringAscent=fontMetrics.getAscent();

        int xo=getWidth()/2-stringWidth/2;
        int yo=getHeight()/2+stringAscent/2;
        g.drawString(text, xo, yo);

    }





}
