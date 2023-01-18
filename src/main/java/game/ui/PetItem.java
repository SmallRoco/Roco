package game.ui;

import Config.AllConfig;
import Config.AttributeAdd;
import Config.ConfigFile;
import game.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class PetItem extends JPanel {

    String petName;
    String type ;
    int[] attributes = new int[8];
    String[] skillNames = new String[4];

    public PetItem(String petName){

        this.petName = petName;

        type = ConfigFile.getType(petName);
        if(type==null)return;
        AttributeAdd petAttributeAdd = ConfigFile.getPetAttributeAdd(petName);

        attributes = petAttributeAdd.getData();
        skillNames = petAttributeAdd.getSkillName();


        this.setBackground(new Color(229, 229, 229));
        this.setBounds(0,0,900,108);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Game.gameMainF.setPetDetails(petName);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                PetItem.this.setBackground(new Color(229, 229, 229));

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                PetItem.this.setBackground(new Color(255,255,255));
            }
        });

    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);
        if(type==null){
            return;
        }




        try {
            g.drawImage(ImageIO.read(new File(AllConfig.rootPath+"宠物/"+petName+"/icon.png")), 20, 20, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        g.drawString(petName,108,60);

        g.drawString(type,180,60);

        g.drawString("精力",240,40);
        g.drawString("物攻",280,40);
        g.drawString("魔攻",320,40);
        g.drawString("防御",360,40);
        g.drawString("魔抗",400,40);
        g.drawString("速度",440,40);
        g.drawString("暴击",480,40);
        g.drawString("闪避",520,40);

        for (int i = 0; i <8; i++) {
            //attributes[i].setText(data[i]+"");
            g.drawString(attributes[i]+"",240+i*40,60);
        }

        for (int i = 0; i <4; i++) {
            //skills[i].setText(skillName[i]+"");
            g.drawString(skillNames[i]+"",580+i*80,60);
        }





    }
}
