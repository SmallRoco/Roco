package game.ui;

import content.BaseFun;

import javax.swing.*;
import java.awt.*;

public class PetList extends MySheel{


    JLayeredPane jLayeredPane = new JLayeredPane();
    PetItem[] petItems ;



    public PetList(LayoutManager layout) {
        super(layout);
        this.setBackground(new Color(255,255,255));
    }

    public void setViews(String[] petNames){
        int len = 0;
        for (int i = 0; i <petNames.length; i++) {
            if(petNames[i]==null)break;
            len++;
        }

        petItems = new PetItem[len];
        for (int i = 0; i <petItems.length; i++) {
            petItems[i] = new PetItem(petNames[i]);
        }


        if (petItems!=null){

            this.setSize(this.getWidth(),petItems[0].getHeight()*petItems.length);
            for (int i = 0; i <petItems.length; i++) {


                petItems[i].setBounds(0,petItems[0].getHeight()*i,petItems[0].getWidth(),petItems[0].getHeight());
                jLayeredPane.add(petItems[i],JLayeredPane.DRAG_LAYER);
                jLayeredPane.moveToFront(petItems[i]);

            }
            //设置滚动阈值  负值
            allS = -(petItems[0].getHeight()*petItems.length-420);
            nowS = 0;

        }
        //this.setBounds(getX(),getY(),getWidth(),1000);

        this.add(jLayeredPane);


    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

    }
}



