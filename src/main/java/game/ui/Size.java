package game.ui;

import java.awt.*;

public class Size {

    public static int WIDTH = 1920;
    public static int HEIGHT = 1080;

    public static int[] SIZEW = new int[41];
    public static int[] SIZEH = new int[41];

    public Size() {

        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = defaultToolkit.getScreenSize();
        HEIGHT = (int)(screenSize.getHeight());
        WIDTH = (int)(screenSize.getWidth());

        for (int i = 1; i <=40; i++) {
            SIZEW[i] = (WIDTH*i)/80;
            SIZEH[i] = (HEIGHT*i)/80;
        }
    }


    public static void set(Component component,double x,double y,double width,double height){

        component.setBounds((int)((WIDTH*x)/80),(int)((HEIGHT*y)/80),(int)((WIDTH*width)/80),(int)((HEIGHT*height)/80));
    }

    public static int getW(double d){

        return (int)(d*WIDTH/80);

    }
    public static int getH(double d){

        return (int)(d*HEIGHT/80);

    }

}
