package game.ui;

import content.Pet;
import game.Game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoxClick extends MouseAdapter {

    public String name;

    public BoxClick(String name){
        this.name = name;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if(Game.myPetS.get(name)==null){
            Game.gameMainF.showMessageRed("暂时还没有这只宠物");
            return;
        }
        Pet[] pets1 = Game.getPets1();
        if(pets1[5]!=null){
            Game.gameMainF.showMessageRed("背包已满");
            return;
        }
        String[] names = new String[6];
        for (int i = 0; i <6; i++) {
            if(pets1[i]==null){names[i] = name;break;}
            names[i] = pets1[i].getName();
        }
        Game.gameMainF.showMessageGreen("操作完成");
        Game.addPetToBox(names,true);
        Game.gameMainF.petDetails.box.setText("放入仓库");
        Game.gameMainF.petDetails.box.removeMouseListener(Game.gameMainF.petDetails.boxClick);
        Game.gameMainF.petDetails.box.addMouseListener(Game.gameMainF.petDetails.boxClick1);

    }
}
