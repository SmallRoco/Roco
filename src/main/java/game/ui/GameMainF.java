package game.ui;

import Config.ConfigFile;
import Config.ImageUtils;
import UI.MainFrame;
import content.Pet;
import game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class GameMainF extends JFrame {
    private int xOld =0;
    private int yOld =0;

    private JLayeredPane jLayeredPane = new JLayeredPane();
    private MyButton petList = new MyButton("宠物列表",18,22,new Color(229, 229, 229),new Color(0,0,0));
    private MyButton myPetList = new MyButton("我的宠物",18,22,new Color(229, 229, 229),new Color(0,0,0));
    private MyButton boxList = new MyButton("宠物背包",18,22,new Color(229, 229, 229),new Color(0,0,0));
    public PetDetails petDetails = new PetDetails(new BorderLayout());
    private PetList petListLabel = new PetList(new BorderLayout());
    private JLabel petIdlePlayer = new JLabel();
    private JLabel messageLabel = new JLabel();

    private int viewFlag = 0;


    public static void main(String[] args) {

        new GameMainF();

    }




    public void addCompant(JComponent jComponent,int x,int y,int w,int h){

        jComponent.setBounds(x,y,w,h);
        jLayeredPane.add(jComponent,200);
        jLayeredPane.moveToFront(jComponent);
    }

    public void setIdleLabelIcon(String name){
        ImageUtils.initPetImage(name);

        ImageIcon currPetAnimal = ImageUtils.getCurrPetAnimal(name + "idle");
        //System.out.println(currPetAnimal.getIconWidth()+" "+currPetAnimal.getIconHeight());
        petIdlePlayer.setBounds(400-currPetAnimal.getIconWidth(),450-currPetAnimal.getIconHeight(),currPetAnimal.getIconWidth(),currPetAnimal.getIconHeight());
        petIdlePlayer.setIcon(currPetAnimal);
        jLayeredPane.moveToFront(petIdlePlayer);
    }

    public void setPetDetails(String name){
        closePage(viewFlag);
        viewFlag = 100;
        petDetails.setPet(name);
        petDetails.setPage(true);
        jLayeredPane.add(petDetails);
        setIdleLabelIcon(name);
    }

    public void changePage(int id){

        showMessageRed("");

        if(id==viewFlag)return;
        closePage(viewFlag);
        if(id<=3){
            petListLabel.setBounds(30,70,petListLabel.getWidth(),petListLabel.getHeight());
            jLayeredPane.add(petListLabel,JLayeredPane.UNDEFINED_CONDITION);
            jLayeredPane.moveToFront(petList);
            jLayeredPane.moveToFront(myPetList);
            jLayeredPane.moveToFront(boxList);

        }
        viewFlag =id;
    }

    public void closePage(int id){
        switch (id){
            case 0:jLayeredPane.remove(petListLabel);repaint();

                break;
            case 1:jLayeredPane.remove(petListLabel);repaint();
                break;
            case 2:jLayeredPane.remove(petListLabel);repaint();
                break;
            case 100:
                jLayeredPane.remove(petDetails);repaint();
                setIdleLabelIcon("null");
                break;
        }
    }

    public void showMessageRed(String s){
        messageLabel.setForeground(new Color(255,0,0));
        messageLabel.setText(s);
    }
    public void showMessageGreen(String s){
        messageLabel.setText(s);
        messageLabel.setForeground(new Color(0,255,0));
    }


    public GameMainF(){

        petListLabel = new PetList(new BorderLayout());
        petListLabel.setViews(ConfigFile.PetNames);



        //初始化宠物 idle动画



        jLayeredPane.add(petIdlePlayer,JLayeredPane.MODAL_LAYER);

        addCompant(messageLabel,0,520,960,20);
        addCompant(petDetails,30,70,900,450);
        addCompant(petListLabel,30,70,900,ConfigFile.PetNames.length*108);
        addCompant(petList,10,10,100,40);
        addCompant(myPetList,120,10,100,40);
        addCompant(boxList,230,10,100,40);


        jLayeredPane.remove(petDetails);


        petList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                petListLabel.setViews(ConfigFile.PetNames);
                changePage(0);
            }
        });
        myPetList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String[] s = new String[Game.myPetS.keySet().size()];
                Game.myPetS.keySet().toArray(s);
                petListLabel.setViews(s);
                changePage(1);
            }
        });
        boxList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String[] s = new String[6];
                Pet[] pets1 = Game.getPets1();
                for (int i =0;i<6;i++) {
                    if(pets1[i]==null)break;
                    s[i] = pets1[i].getName();
                }
                petListLabel.setViews(s);
                changePage(2);
            }
        });





        //拖动
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                xOld = e.getX();
                yOld = e.getY();
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                int xOnScreen = e.getXOnScreen();//鼠标的绝对坐标
                int yOnScreen = e.getYOnScreen();
                int xx = xOnScreen - xOld;
                int yy = yOnScreen - yOld;
                GameMainF.this.setLocation(xx, yy);
            }
        });
        this.add(jLayeredPane);
        this.setUndecorated(true);
        this.setLocation(480,270);
        this.setSize(960,540);
        //this.getContentPane().setBackground(new Color(255,255,255));
        this.setVisible(true);
    }
}
