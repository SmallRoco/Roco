package game.ui;

import content.Pet;
import game.Game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoxClick1 extends MouseAdapter {

    public String name;

    public BoxClick1(String name){
        this.name = name;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        Pet[] pets1 = Game.getPets1();
        if(pets1[1]==null){
            Game.gameMainF.showMessageRed("背包中至少要有一只宠物");
            return;
        }
        String[] names = new String[6];
        int index = 0;
        for (int i = 0; i <6; i++) {
            if(pets1[i]==null)break;
            if(!pets1[i].getName().equals(name)){
                names[index++] = pets1[i].getName();
            }
        }
        Game.gameMainF.showMessageGreen("操作完成");
        Game.addPetToBox(names,true);
        Game.gameMainF.petDetails.box.setText("放入背包");
        Game.gameMainF.petDetails.box.removeMouseListener(Game.gameMainF.petDetails.boxClick1);
        Game.gameMainF.petDetails.box.addMouseListener(Game.gameMainF.petDetails.boxClick);
    }
}
