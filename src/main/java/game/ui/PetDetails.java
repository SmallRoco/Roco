package game.ui;

import Config.AttributeAdd;
import Config.ConfigFile;
import Config.ImageUtils;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;
import content.SkillManager;
import game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PetDetails extends JPanel{

    String petName;
    String type ;
    int[] attributes = new int[8];
    String[] skillNames = new String[4];
    private JLabel petIdlePlayer = new JLabel();
    private JTextField[] attributesT = new JTextField[8];
    private JTextField[] skillsT = new JTextField[4];
    private JLayeredPane jLayeredPane = new JLayeredPane();
    private MyButton sure = new MyButton("保存",18,22,new Color(198, 198, 198),new Color(0,0,0));
    private MyButton showCanSkills = new MyButton("查看可学技能",18,22,new Color(198, 198, 198),new Color(0,0,0));
    private JTextArea jTextArea = new JTextArea();
    private MyButton chanllage = new MyButton("挑战",18,22,new Color(198, 198, 198),new Color(0,0,0));
    public MyButton box = new MyButton("",18,22,new Color(198, 198, 198),new Color(0,0,0));
    private MyButton first = new MyButton("首选",18,22,new Color(198, 198, 198),new Color(0,0,0));

    // true 为显示基本信息，false 为显示 可学习的技能
    private boolean showFlag = true;



    public PetDetails(LayoutManager layout) {
        super(layout);

        this.setBackground(new Color(229, 229, 229));

        petIdlePlayer = new JLabel();
        for (int i = 0; i <8; i++) {
            attributesT[i] = new JTextField();
            attributesT[i].setBounds(420+(i%4)*80,(i/4)*80+50,60,30);
            attributesT[i].setFont(new Font("楷书",Font.PLAIN,18));
            attributesT[i].setBorder(null);
            //设置只能输入数字
            attributesT[i].addKeyListener(new KeyAdapter(){
                public void keyTyped(KeyEvent e) {
                    int keyChar = e.getKeyChar();
                    if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){

                    }else{
                        e.consume(); //关键，屏蔽掉非法输入
                    }
                }
            });
            jLayeredPane.add(attributesT[i],JLayeredPane.MODAL_LAYER);
        }
        for (int i = 0; i < 4; i++) {
            skillsT[i] = new JTextField();
            skillsT[i].setBounds(420+(i%2)*150,(i/2)*60+200,120,30);
            skillsT[i].setFont(new Font("楷书",Font.BOLD,18));
            skillsT[i].setBorder(null);
            jLayeredPane.add(skillsT[i],JLayeredPane.MODAL_LAYER);
        }

        sure.setBounds(600,340,60,40);
        showCanSkills.setBounds(700,340,150,40);
        first.setBounds(500,340,60,40);
        box.setBounds(600,390,120,40);
        chanllage.setBounds(500,390,60,40);

        sure.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int[] ints = new int[8];
                for (int i = 0; i <8; i++) {
                    ints[i] = Integer.parseInt(attributesT[i].getText());
                }
                if(Game.setAttributes(petName,ints)==-1){
                    Game.gameMainF.showMessageRed("输入数据之和要小于800,请重试");
                    return;
                }
                String[] strings = new String[4];
                for (int i = 0; i <4; i++) {
                    strings[i] = skillsT[i].getText();
                }
                int i = Game.setSkills(petName, strings);
                if(i==-1){
                    Game.gameMainF.showMessageRed("无法携带重复技能");
                    return;
                }else if(i>=0&&i<4){
                    Game.gameMainF.showMessageRed("该宠物没有此技能:"+strings[i]);
                    return;

                }
                Game.gameMainF.showMessageGreen("设置成功!");
            }
        });

        showCanSkills.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(showFlag) {
                    setPage(false);
                }else {
                    setPage(true);
                }
            }
        });

        chanllage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        if(showFlag) {
                            setPage(false);
                        }else {
                            setPage(true);
                        }
                        Game.chanllage(petName);

                    }
                }).start();

            }
        });

        first.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!Game.setBoxFirst(petName)){
                    Game.gameMainF.showMessageRed("请先将该宠物放入背包");
                }else {
                    Game.gameMainF.showMessageGreen("操作完成");
                }
            }
        });



        jLayeredPane.add(sure,JLayeredPane.MODAL_LAYER);
        jLayeredPane.add(showCanSkills,JLayeredPane.MODAL_LAYER);
        jLayeredPane.add(chanllage,JLayeredPane.MODAL_LAYER);
        jLayeredPane.add(box,JLayeredPane.MODAL_LAYER);
        jLayeredPane.add(first,JLayeredPane.MODAL_LAYER);



        this.add(jLayeredPane);


    }

    public void setPage(boolean showFlag){
        if(showFlag==this.showFlag)return;
        this.showFlag = showFlag;
        if(showFlag){
            for (int i = 0; i <8; i++) {
                jLayeredPane.add(attributesT[i],JLayeredPane.MODAL_LAYER);
            }
            for (int i = 0; i < 4; i++) {
                jLayeredPane.add(skillsT[i],JLayeredPane.MODAL_LAYER);
            }
            jLayeredPane.add(sure,JLayeredPane.MODAL_LAYER);
            jLayeredPane.add(chanllage,JLayeredPane.MODAL_LAYER);
            jLayeredPane.add(box,JLayeredPane.MODAL_LAYER);
            jLayeredPane.add(first,JLayeredPane.MODAL_LAYER);
            Game.gameMainF.setIdleLabelIcon(petName);
            jLayeredPane.remove(jTextArea);
            PetDetails.this.repaint();
            showFlag = true;
            showCanSkills.setText("查看可学技能");
        }else {
            jLayeredPane.removeAll();
            jLayeredPane.add(showCanSkills,JLayeredPane.MODAL_LAYER);
            PetDetails.this.repaint();
            Game.gameMainF.setIdleLabelIcon("null");
            showFlag = false;
            StringBuilder stringBuilder = new StringBuilder();
            String[] strings = ConfigFile.petCanSkills.get(petName);
            for (int i = 0; i < strings.length; i++) {
                SkillInterface skillInterface = SkillManager.getHashMap().get(strings[i]);
                int speed = skillInterface.getSpeed();
                if(strings.length>11){
                    stringBuilder.append(strings[i] + Game.getSpace(24 - strings[i].length() * 4) + skillInterface.getType() + Game.getSpace(12 - skillInterface.getType().length() * 4) + "速度:" + speed + Game.getSpace(4 - speed / 10) + SkillManager.getHashMap().get(strings[i]).getDescription() + "\n");
                }else {
                    stringBuilder.append(strings[i] + Game.getSpace(24 - strings[i].length() * 4) + skillInterface.getType() + Game.getSpace(12 - skillInterface.getType().length() * 4) + "速度:" + speed + Game.getSpace(4 - speed / 10) + SkillManager.getHashMap().get(strings[i]).getDescription() + "\n\n");
                }
            }
            jTextArea.setText(stringBuilder.toString());
            jLayeredPane.add(jTextArea,JLayeredPane.MODAL_LAYER);
            showCanSkills.setText("返回");
        }
    }




    public BoxClick boxClick = new BoxClick(petName);
    public BoxClick1 boxClick1 = new BoxClick1(petName);
    public void setPet(String petName){

        box.removeMouseListener(boxClick1);
        box.removeMouseListener(boxClick);
        boxClick = new BoxClick(petName);
        boxClick1 = new BoxClick1(petName);

        this.petName = petName;
        type = ConfigFile.getType(petName);
        if(type==null)return;
        AttributeAdd petAttributeAdd = ConfigFile.getPetAttributeAdd(petName);


        Pet[] pets1 = Game.getPets1();
        //判断是否带入背包
        int withFlag =0;
        for (int i = 0; i <pets1.length; i++) {
            if(pets1[i]==null)break;
            if(pets1[i].getName().equals(petName)){
                withFlag = 1;
                break;
            }
        }
        if(withFlag==1){
            box.removeMouseListener(boxClick);
            box.addMouseListener(boxClick1);
            box.setText("放入仓库");
        }else {
            box.removeMouseListener(boxClick1);
            box.addMouseListener(boxClick);
            box.setText("放入背包");
        }

        attributes = petAttributeAdd.getData();
        skillNames = petAttributeAdd.getSkillName();


        for (int i = 0; i < 8; i++) {
            attributesT[i].setText(""+attributes[i]);
        }
        for (int i = 0; i < 4; i++) {
            skillsT[i].setText(skillNames[i]);
        }
        jTextArea.setLineWrap(true);        //激活自动换行功能
        jTextArea.setWrapStyleWord(true);            // 激活断行不断字功能
        jTextArea.setBackground(new Color(229,229,229));
        //jTextArea.setFont(new Font("宋体",Font.PLAIN,18));
        jTextArea.setBounds(0,0,900,450);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if(showFlag){



        for (int i = 0; i < 8; i++) {
            String s = null;
            switch (i){
                case 0:s="精力";
                    break;
                case 1:s="物攻";
                    break;
                case 2:s="魔攻";
                    break;
                case 3:s="防御";
                    break;
                case 4:s="魔抗";
                    break;
                case 5:s="速度";
                    break;
                case 6:s="暴击";
                    break;
                case 7:s="闪避";
                    break;

            }

            g.drawString(s,420+(i%4)*80,(i/4)*80+40);
        }

            g.setFont(new Font("楷书",Font.BOLD,32));
            g.drawString(petName,100,425);

            g.drawString("属性:"+type,300,425);
        }else {

            /*String[] strings = ConfigFile.petCanSkills.get(petName);
            for (int i = 0; i < strings.length; i++) {
                g.drawString(strings[i]+":"+ SkillManager.getHashMap().get(strings[i]).getDescription(),0,i*50);
            }*/

        }

    }
}
