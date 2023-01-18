package UI;

import Config.ImageUtils;
import content.BaseFun;
import content.Pet;
import game.FightStart;
import game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PetItemLabel extends JPanel {

    Pet pet;

    public PetItemLabel(int id){

        pet = FightStart.getPets1()[id];


        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if("宠物".equals(MainFrame.changePet.getText())||PetItemLabel.this.pet==MainFrame.pet1){return;}
                if(PetItemLabel.this.pet!=null&&PetItemLabel.this.pet.getHp()>0){
                    MainFrame.useSkill[0] = 10+ FightStart.getIndexPet(FightStart.getPets1(),PetItemLabel.this.pet);
                    if(Game.connectFlag){
                        Game.write(MainFrame.useSkill[0]+"");
                    }
                    MainFrame.hidePetLabels();
                    MainFrame.showSkillLabel();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if("宠物".equals(MainFrame.changePet.getText())||PetItemLabel.this.pet==MainFrame.pet1){return;}
                if(PetItemLabel.this.pet!=null&&PetItemLabel.this.pet.getHp()>0){
                    PetItemLabel.this.setBounds(getX(),430,getWidth(),getHeight());
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if("宠物".equals(MainFrame.changePet.getText())||PetItemLabel.this.pet==MainFrame.pet1){return;}
                PetItemLabel.this.setBounds(getX(),435,getWidth(),getHeight());

            }
        });



        this.setCursor(new Cursor(Cursor.HAND_CURSOR));

    }


    @Override
    public void paint(Graphics g) {

        g.setColor(new Color(9, 80, 155));
        g.fillRect(0,0,68,88);
        if(pet==null) {
            return;
        }
        g.drawImage(ImageUtils.getPetIcon(pet.getName()),0,0,null);
        g.setColor(new Color(255, 195, 0));
        g.fillRect(0,68,68*pet.getHp()/pet.getBaseHp(),20);
        g.setColor(new Color(0, 0, 0));
        g.drawString(pet.getHp()+"/"+pet.getBaseHp(),0,88);

    }
}
