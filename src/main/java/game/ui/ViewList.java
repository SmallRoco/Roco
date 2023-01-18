package game.ui;

import javax.swing.*;
import java.awt.*;

public class ViewList extends MySheel{

    JLayeredPane jLayeredPane = new JLayeredPane();

    public ViewList(LayoutManager layout) {
        super(layout);
        this.setBackground(new Color(255,255,255));
    }

    public void setViews(JPanel[] jPanels){


        if (jPanels!=null){

            this.setSize(this.getWidth(),jPanels[0].getHeight()*jPanels.length);


            for (int i = 0; i <jPanels.length; i++) {

                jPanels[i].setBounds(0,jPanels[0].getHeight()*i,getWidth(),jPanels[0].getHeight());
                jLayeredPane.add(jPanels[i],JLayeredPane.DRAG_LAYER);

            }
            //设置滚动阈值  负值
            allS = -(getHeight()-Size.getH(34));

        }

        this.add(jLayeredPane);


    }
}
