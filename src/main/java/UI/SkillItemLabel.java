package UI;

import Config.AllConfig;
import Config.ImageUtils;
import content.BaseFun;
import content.SkillInterface;
import game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SkillItemLabel extends JLabel {


    SkillInterface skill;

    int id;
    int pp;
    boolean focus = false;
    static ImageIcon itemImage = new ImageIcon(AllConfig.rootPath + "UI/技能栏/item.PNG");
    static ImageIcon itemFocusImage = new ImageIcon(AllConfig.rootPath + "UI/技能栏/itemFocus.PNG");
    static ImageIcon itemCloseImage = new ImageIcon(AllConfig.rootPath + "UI/技能栏/itemClose.PNG");


    public int getPp() {
        return pp;
    }

    public void setPp(int pp) {
        this.pp = pp;
        if (pp<=0){
            this.setIcon(itemCloseImage);
        }else {
            this.setIcon(itemImage);
        }
        repaint();
    }

    public void setSkill(SkillInterface skill){
        this.pp = MainFrame.pet1.getPps()[BaseFun.getSkillIndex(MainFrame.pet1,skill.getName())];
        this.skill = skill;
        if (pp<=0){
            this.setIcon(itemCloseImage);
        }else {
            this.setIcon(itemImage);
        }
        repaint();
    }

    public SkillItemLabel(int id, SkillInterface skill) {
        this.id = id;
        this.skill = skill;
        this.setIcon(itemImage);
        if(skill==null)return;
        this.pp = skill.getPp();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if("战斗".equals(MainFrame.changePet.getText())){return;}
                if(pp>0) {
                    MainFrame.useSkill[0] = SkillItemLabel.this.id;
                    if(Game.connectFlag){
                        Game.write(MainFrame.useSkill[0]+"");
                    }
                    SkillItemLabel.this.setIcon(itemImage);
                }

            }
        });



        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if("战斗".equals(MainFrame.changePet.getText())){return;}
                if(pp>0) {
                    focus = true;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            MainFrame.sleep(1000);
                            if(SkillItemLabel.this.focus){
                                MainFrame.openSkillMessage(SkillItemLabel.this.skill.getDescription(),id);
                            }
                        }
                    }).start();
                    if (MainFrame.useSkill[0] >= 0) return;
                    SkillItemLabel.this.setIcon(itemFocusImage);

                }

            }

            @Override
            public void mouseExited(MouseEvent e) {
                if("战斗".equals(MainFrame.changePet.getText())){return;}
                MainFrame.closSkillMessage(SkillItemLabel.this.skill.getDescription());
                if(pp>0) {
                    focus = false;
                    SkillItemLabel.this.setIcon(itemImage);
                }

            }
        });
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if(pp>0) {
            if (skill != null) {
                //画名字
                g.setColor(new Color(255, 255, 255));
                g.setFont(new Font("宋体", Font.BOLD, 18));
                g.drawString(this.skill.getName(), 70, 28);

                //画威力
                g.setFont(new Font("宋体", Font.PLAIN, 15));
                g.drawString("威力:" + this.skill.getPower(), 70, 55);

                //画速度
                g.setFont(new Font("楷书", Font.BOLD, 25));
                if (skill.getSpeed() >= 10) {
                    g.drawString("" + skill.getSpeed(), 10, 85);
                    g.drawString("" + skill.getSpeed(), 12, 85);
                } else {
                    g.drawString("" + skill.getSpeed(), 20, 86);
                    g.drawString("" + skill.getSpeed(), 22, 86);
                }

                //画pp
                g.setColor(new Color(255, 255, 255));
                g.setFont(new Font("宋体", Font.BOLD, 14));
                g.drawString(pp + "/" + this.skill.getPp(), 99, 87);
                g.drawString(pp + "/" + this.skill.getPp(), 101, 87);
                g.setColor(new Color(0, 0, 0));
                g.setFont(new Font("宋体", Font.BOLD, 14));
                g.drawString(pp + "/" + this.skill.getPp(), 100, 87);
                //画属性
                g.drawImage(ImageUtils.getTypeLarge(skill.getType()), 8, 2, null);

            }
        }

    }
}
