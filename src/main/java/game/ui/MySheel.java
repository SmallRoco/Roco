package game.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

//滑轮滚动屏幕
public class MySheel extends JPanel{

    //滚轮滑动偏移量 限制；
    protected int nowS = 0;
    //可以下滑一百个单位   ****修改此处，页面的整体大小也要改
    protected int allS ;

    private int SPEED = 3;

    public MySheel(LayoutManager layout) {

        super(layout);

        //添加滚轮事件
        MySheel.this.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                //允许有一个单位的误差，因为getH(10) 不等于 getH(1)*10;  这将会导致总体边界不足
                if (e.getWheelRotation() == 1 && nowS > allS+Size.getH(SPEED)) {
                    nowS -= Size.getH(SPEED);
                    MySheel.this.setBounds(MySheel.this.getX(), MySheel.this.getY() - Size.getH(SPEED), MySheel.this.getWidth(), MySheel.this.getHeight());
                    MySheel.this.repaint();
                }
                else if (e.getWheelRotation() == -1 && nowS < 0) {
                    MySheel.this.setBounds(MySheel.this.getX(), MySheel.this.getY() + Size.getH(SPEED), MySheel.this.getWidth(), MySheel.this.getHeight());
                    nowS += Size.getH(SPEED);
                    MySheel.this.repaint();
                }

            }
        });

    }

    public MySheel() {

    }
}
