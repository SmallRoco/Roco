package UI;

import Config.*;
import Utils.TestJlabel;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;
import content.SkillManager;
import content.netpetshow.SkillN;
import content.space.SpaceInterface;
import game.FightStart;
import game.Game;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Objects;

public class MainFrame extends JFrame {


    //用来表示先手
    public static boolean first = true;


    //用来做窗口的拖动效果
    static int xOld = 0;
    static int yOld = 0;

    //下一只出场宠物的增益
    public static SkillN nextPetShow1;
    public static SkillN nextPetShow2;

    //游戏结束的标志
    public volatile static boolean gameOverFlag = false;
    public volatile static SpaceInterface space = null;

    public synchronized static SpaceInterface getSpace() {
        return space;
    }

    public synchronized static void setSpace(SpaceInterface space) {
        MainFrame.space = space;
    }

    //Toolkit.getDefaultToolkit().createImage("F:\\洛克王国资源\\大寒\\sprites\\DefineSprite_88\\frames.gif
    public static JLayeredPane jLayeredPane ;
    //public static Fighting fighting = new Fighting();
    public static JLabel backGround;
    //两个用来显示宠物gif的label
    public static AnimalLabel pawn1;
    public static JLabel pawn2 ;
    //宠物血条
    public static BloodLine bloodLine1 ;
    public static BloodLine bloodLine2 ;
    //宠物图标 信息框
    public static IconLabel icon1 ;
    public static IconLabel icon2 ;
    //技能栏背景
    public static JLabel skillLabel ;
    //换宠按钮
    public static JLabel changePet ;
    //逃跑按钮
    public static JLabel runLabel;
    //四个技能条目
    public static SkillItemLabel[] skillItemLabels ;
    //六个宠物条目
    public static PetItemLabel[] petItemLabels ;
    //异常效果动画播放器  睡眠，冰冻....
    public static JLabel[] statusLabel ;
    //宠物状态栏
    public static JLabel[] statusShowLabel ;
    //脱手技能播放器   空气切割.热力爆弹....
    public static JLabel tuoShouSkillPlayer ;

    //环境label   环境天气没有区分开 后面才知道有天气和环境之分
    public static JLabel environmentLabel ;

    //宠物死亡的标志， 01为右面死，10为左面死
    public static int deadFlag = -1;


    //标记是否暴击
    public volatile static boolean boomFlag ;
    //标记是否miss
    public volatile static boolean missFlag ;
    //标记是否克制        1克制   -1 抵抗
    public volatile static int keZhiFlag ;

    //场上环境
    public static String environment = null;
    //环境持续的回合数
    public static int environmentCount;
    //public static boolean environmentLock = false;
    //环境被锁定的回合数
    public static int environmentLockCount = 0;

    /**
     * 环境是否被锁定
     * @return
     */
    public static boolean isEnvironmentLock() {
        return environmentCount>0;
    }

    /**
     * 锁定与解锁天气
     * @param count     回合数
     */
    public static void setEnvironmentLock(int count) {
        //MainFrame.environmentLock = environmentLock;
        environmentLockCount = count;
    }

    /**
     * 设置环境
     * @param en        环境名
     * @param count     回合数
     * @return      是否成功
     */
    public static boolean setEnvironment(String en, int count){
        if(!ConfigFile.ENVIRONMENT_START)return false;
        if(environmentLockCount>0)return false;     //天气被锁定
        environment = en;
        environmentCount = count;
        environmentLabel.setText(en);
        return true;
    }
    public static String getEnvironment(){
        if(!ConfigFile.ENVIRONMENT_START)return "";
        if(environment==null)return "";
        return environment;
    }

    public static void addNextPetShow(Pet pet,SkillN skillN){
        if(pet==pet1){
            nextPetShow1 = skillN;
        }else {
            nextPetShow2 = skillN;
        }
    }

    /**
     *
     */
    public static void initUi(){
        first = true;

        //窗口的拖动就不重置了 保留上一次的位置
        /*xOld = 0;
        yOld = 0;*/

        nextPetShow1 = null;
        nextPetShow2 = null;

        gameOverFlag = false;
        SpaceInterface space = null;



        //Toolkit.getDefaultToolkit().createImage("F:\\洛克王国资源\\大寒\\sprites\\DefineSprite_88\\frames.gif
        jLayeredPane = new JLayeredPane();
        //public static Fighting fighting = new Fighting();
        backGround = new JLabel();
        pawn1 = new AnimalLabel();
        pawn2 = new JLabel();
        bloodLine1 = new BloodLine();
        bloodLine2 = new BloodLine();

         icon1  = new IconLabel();
         icon2 = new IconLabel();

        skillLabel = new JLabel();
        changePet = new JLabel("宠物");
        runLabel = new JLabel("逃跑");
        skillItemLabels = new SkillItemLabel[4];
        petItemLabels = new PetItemLabel[6];
        //异常效果动画播放器  睡眠，冰冻....
        statusLabel = new JLabel[]{new JLabel(),new JLabel()};
        statusShowLabel = new JLabel[]{new JLabel(),new JLabel()};
        //脱手技能播放器   空气切割.热力爆弹....
        tuoShouSkillPlayer = new JLabel();

        //环境
        environmentLabel = new JLabel();


        //标记是否暴击
        boomFlag = false;
        //标记是否miss
        missFlag = false;
        //标记是否克制        1克制   -1 抵抗
        keZhiFlag = 0;

        environment = null;
        environmentCount = 0;
        environmentLockCount = 0;

        messageLabel = new JLabel();
        showDamageLabel = new JLabel();


        Pet pet1 = null;
        Pet pet2 = null;

        tuoShouFlag = false;
        count =0;
        useSkill[0] = -1;
        useSkill[1] = -1;
        useSkillOld[0] =  -1;
        useSkillOld[1] = -1;


        deadFlag = -1;
    }



    //消息框 用来显示技能详情
    public static JLabel messageLabel;

    //显示伤害和回血值得label
    public static JLabel showDamageLabel ;


    //目前场上对战的两只宠物
    public static Pet pet1;
    public static Pet pet2;


    /**
     * 显示宠物状态
     */
    public static void showStatusMessage(){

        jLayeredPane.moveToFront(statusShowLabel[0]);
        jLayeredPane.moveToFront(statusShowLabel[1]);
        statusShowLabel[0].setText("<html>"+pet1.getStatusToString()+"</html>");
        statusShowLabel[1].setText("<html>"+pet2.getStatusToString()+"</html>");
    }



    /**
     * 显示伤害
     * @param damage    数值
     * @param pawn1     是否是玩家1
     */
    public static void showDamage(int damage,boolean pawn1){
        String s = "        ";
        if(!pawn1)s="                          ";

        if(keZhiFlag==1){
            s+="克制";
            keZhiFlag = 0;
        }else if(keZhiFlag==-1){
            s+="抵抗";
            keZhiFlag = 0;
        }


        jLayeredPane.moveToFront(showDamageLabel);

        //Miss
        if(missFlag){
            showDamageLabel.setForeground(new Color(0, 255, 255));
            showDamageLabel.setText(s+"Miss");
            missFlag = false;
        }else
        //暴击
        if(boomFlag){
            showDamageLabel.setForeground(new Color(255, 255, 255));
            showDamageLabel.setText(s+"-"+damage);
            boomFlag = false;
        }else
        //普通伤害
        if(damage>0){
            showDamageLabel.setForeground(new Color(255, 68, 68));
            showDamageLabel.setText(s+"-"+damage);
        }else if(damage<0){
            //回血
            showDamageLabel.setForeground(new Color(21, 255, 21));
            showDamageLabel.setText(s+"+"+-damage);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                sleep(1000);
                showDamageLabel.setText("");
            }
        }).start();
    }

    /**
     * 显示技能详细信息
     */
    public static void openSkillMessage(String s,int id){
        messageLabel.setBounds(251 + 180 * id, 418-(int)(s.length()*1.8), 155, (int)(s.length()*1.8));
        messageLabel.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;"+s+"</html>");
        jLayeredPane.moveToFront(messageLabel);
    }

    /**
     * 关闭技能提示信息
     */
    public static void closSkillMessage(String s){
        messageLabel.setBounds(0,0,0,0);
        messageLabel.setText("");
    }

    //场景函数

    /**
     * 播放状态动画
     */
    public static void playStatus(String name,boolean left){

        showStatusMessage();
        int index = 1;
        ImageIcon statusAnimal = ImageUtils.getStatusAnimal(name);
        if(left) {
            index = 0;
            statusLabel[0].setBounds(0,418-statusAnimal.getIconHeight(),statusAnimal.getIconWidth(),statusAnimal.getIconHeight());
        }else {
            statusLabel[0].setBounds(960-statusAnimal.getIconWidth(),418-statusAnimal.getIconHeight(),statusAnimal.getIconWidth(),statusAnimal.getIconHeight());
        }
        //有些动画要放在人物后边
        if(!"中毒开始".equals(name)) {
            jLayeredPane.moveToFront(statusLabel[index]);
        }
        statusLabel[index].setIcon(ImageUtils.getStatusAnimal(name));

        //各种属性提升下降，动画只有0.5秒
        if(name.contains("寄生")){
            if(name.contains("开始")){
                sleep(450);
            }
            sleep(450);

        }
        sleep(500);
        statusLabel[index].setIcon(null);
    }

    //标记当前是否在播放脱手动画，脱手动画应位于顶层
    static boolean tuoShouFlag = false;

    /**
     * 将 magic 动画 和脱手技能绑定起来，一起播放
     * @param pet           宠物
     * @param actionnmae    宠物动画名 magic
     * @param name  脱手技能名称
     * @param flag  是不是玩家1
     */
    public static void playBond(Pet pet,String actionnmae,String name,boolean flag){

        int time = ImageUtils.getTuoShouSkillAnimals(name).getTime();

        int time1 = Animals.getAction(pet.getName(), actionnmae).getTime();

        if(time1>=time){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    playTuoShouSkill(name,flag);
                }
            }).start();
            pawnRun(pet,actionnmae);

        }else {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    pawnRun(pet,actionnmae);
                }
            }).start();

            playTuoShouSkill(name,flag);

        }

    }


    /**
     * 播放脱手技能动画
     */
    public synchronized static void playTuoShouSkill(String name,boolean flag){


        if(flag){name+="R";}
        ImageAnimal tuoShouSkillAnimals = ImageUtils.getTuoShouSkillAnimals(name);
        if(tuoShouSkillAnimals==null)return;

        if(flag) {
            tuoShouSkillPlayer.setBounds(0, 418 - tuoShouSkillAnimals.getImage().getIconHeight(), tuoShouSkillAnimals.getImage().getIconWidth(), tuoShouSkillAnimals.getImage().getIconHeight());
        }else {
            tuoShouSkillPlayer.setBounds(960-tuoShouSkillAnimals.getImage().getIconWidth(), 418 - tuoShouSkillAnimals.getImage().getIconHeight(), tuoShouSkillAnimals.getImage().getIconWidth(), tuoShouSkillAnimals.getImage().getIconHeight());
        }
        tuoShouFlag = true;
        tuoShouSkillPlayer.setIcon(tuoShouSkillAnimals.getImage());
        jLayeredPane.moveToFront(tuoShouSkillPlayer);
        sleep(tuoShouSkillAnimals.getTime());
        tuoShouSkillPlayer.setIcon(null);
        tuoShouFlag = false;
    }

    /**
     *  播放动画
     */
    public  static void pawnRun(Pet pet,String actionnmae){

        int width = 570 ;
        ImageIcon currPetAnimal = ImageUtils.getCurrPetAnimal(pet.getName() + actionnmae);
        if("att1".equals(actionnmae)) {
            jLayeredPane.moveToFront(pet.getPawn());

            if(pet1==pet){
                ImageIcon currPetAnimal1 = ImageUtils.getCurrPetAnimal(pet.getName() + "att2");
                if(currPetAnimal1 !=null){
                    currPetAnimal = currPetAnimal1;
                    pawn1.att2 = true;
                }
            }
            width = currPetAnimal.getIconWidth();

        }
        if(tuoShouFlag){jLayeredPane.moveToFront(tuoShouSkillPlayer);}


        if(currPetAnimal==null)return;

        //播放动画
        pet.getPawn().setIcon(currPetAnimal);
        if(pet==pet2){
            pet.getPawn().setBounds(390-(width-570),418-currPetAnimal.getIconHeight(),width,currPetAnimal.getIconHeight());}else {
            pet.getPawn().setBounds(0, 418 - currPetAnimal.getIconHeight(), width, currPetAnimal.getIconHeight());

        }

                //延迟一会
        if(!"idle".equals(actionnmae)) {
            sleep(Objects.requireNonNull(Animals.getAction(pet.getName(), actionnmae)).getTime() - 50);
        }
                //动画播放完成，角色回到idle状态


        /*if(pet==pet2)pet.getPawn().setBounds(390-(width-570),418-currPetAnimal.getIconHeight(),width,currPetAnimal.getIconHeight());
        else pet.getPawn().setBounds(0,418-currPetAnimal.getIconHeight(),width,currPetAnimal.getIconHeight());
*/
        if("dead".equals(actionnmae)) {
            pet.getPawn().setIcon(null);
        }else if(!"idle".equals(actionnmae)){
            pawnRun(pet,"idle");
        }

        if("att1".equals(actionnmae)) {
            if(pawn1.att2){
                pawn1.att2=false;
            }
            flash();
        }



    }


    /**
     * 血量改变 要显示伤害以及更新血条
     * @param pet
     * @param damage    伤害值 负数为回血
     */
    public static void bloodChange(Pet pet,int damage){
        if(pet!=pet1&&pet!=pet2)return;
        showDamage(damage,pet==pet1);
        //game over 已经触发完了
        //if(pet.getHp()<0){pet.setHp(0);}

        pet.getBloodLine().change(pet.getHp());
    }

    /**
     * 游戏结束
     */
    public static void gameOver(){
        gameOverFlag = true;
        if(Game.connectFlag){
            Game.connectClose();
        }
    }

    /**
     * 宠物死了  换宠     只有玩家1需要换宠 玩家2自动换宠(电脑人) 或者 他在他那里是玩家1(联网)
     * @param pet
     */
    public static void dead(Pet pet){



        if(pet==pet1) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (useSkill[0]!=-1) {
                        sleep(20);
                    }
                    hideSkillLabel();
                    showPetLabels();
                    changePet.setText("战斗");
                }
            }).start();

        }else {

        }
    }

    /**
     * 获取死亡对象
     */


    /**
     * 上一次用的技能
     */
    public volatile static int[] useSkillOld = new int[]{-1,-1};
    public volatile static int[] useSkill = new int[]{-1,-1};



    private static MainFrame mainFrame ;
    public static void close(){
        mainFrame.dispose();
        gameOver();
    }

    //用于标记电脑人现在上到第几个宠物了
    public static   int count = 0;
    public static boolean start(Pet p1,Pet p2) {

        gameOverFlag = false;
        mainFrame = new MainFrame();


        initAttribute(p1,p2);

        //p1.addStatus("睡眠");
        //pawn1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(Animals.getAction("大寒", "idle").getUri())));
        //pawn2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(Animals.getAction("大寒", "idle").getUri())));



        while (!gameOverFlag){

            if(useSkill[0]>=0&&useSkill[1]>=0){

                //回合开始
                if(moreSpeed(pet2,pet1,useSkill[1],useSkill[0] )){

                    if(!pet2.statusHandler()){
                        petUseSkill(pet2,useSkill[1],pet1);
                    }
                    if(gameOverFlag) {
                        break;
                    }
                    sleep(800);
                    if(!pet1.statusHandler())petUseSkill(pet1,useSkill[0],pet2);;

                }else {
                    if(!pet1.statusHandler())petUseSkill(pet1,useSkill[0],pet2);;
                    if (gameOverFlag) {
                        break;
                    }
                    sleep(800);
                    if(!pet2.statusHandler())petUseSkill(pet2,useSkill[1],pet1);;
                }



                pet1.pass();
                pet2.pass();
                if(space!=null){
                    space.pass(1);
                    if(space.isEnd()){
                        space = null;
                    }
                }
                if(environmentCount>0&&environmentCount<1000)environmentCount--;
                if(environmentCount==0){
                    environment = null;
                    environmentLabel.setText("");
                }
                if(environmentLockCount>0&&environmentLockCount<1000){
                    environmentLockCount--;
                    if(environmentLockCount==0){

                    }
                }
                //回合结束

                for (int i = 0; i <1; i++) {
                    if(useSkill[i]>=0&&useSkill[i]<4){
                        useSkillOld[i] = useSkill[i];
                    }
                }
                useSkill[0]=-1;useSkill[1]=-1;

            }else {

                //如果己方宠物都死了
                if(BaseFun.getPetCountInDead(FightStart.getPets1())==BaseFun.getPetsCount(FightStart.getPets1())){
                    return false;
                }

                //如果对面没出招
                if(useSkill[1]<0) {

                    if (pet2.getHp() > 0) {
                        if(pet1.getHp()<=0){
                            useSkill[1]= 9;
                        }else {
                            pet2.getAi().useSkill(pet2, pet1);
                        }
                    } else {

                        count =0;

                        if (FightStart.getPets2()[count] == null) return true;
                        while (FightStart.getPets2()[count].getHp()<=0){
                            count++;
                            if (FightStart.getPets2()[count] == null) return true;
                        }
                        useSkill[1] = 10 + count;
                    }


                }

                if(useSkill[0]<0&&deadFlag>0){
                    int i = deadFlag/100-1;
                    if(i>=0){
                        useSkill[0]=i;
                    }

                    deadFlag =-1;
                }




                sleep(50);
            }



        }
        return false;



    }
    public static boolean connectStart(Pet p1,Pet p2) {

        gameOverFlag = false;
        mainFrame = new MainFrame();

        initAttribute(p1,p2);
        //pawn1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(Animals.getAction("大寒", "idle").getUri())));
        //pawn2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(Animals.getAction("大寒", "idle").getUri())));

        while (!gameOverFlag){

            if(useSkill[0]>=0&&useSkill[1]>=0){

                //回合开始
                if(moreSpeed(pet2,pet1,useSkill[1],useSkill[0] )){

                    if(!pet2.statusHandler()){
                        petUseSkill(pet2,useSkill[1],pet1);
                    }
                    if(gameOverFlag) {
                        break;
                    }
                    sleep(800);
                    if(!pet1.statusHandler())petUseSkill(pet1,useSkill[0],pet2);;

                }else {
                    if(!pet1.statusHandler())petUseSkill(pet1,useSkill[0],pet2);;
                    if (gameOverFlag) {
                        break;
                    }
                    sleep(800);
                    if(!pet2.statusHandler())petUseSkill(pet2,useSkill[1],pet1);;
                }



                pet1.pass();
                pet2.pass();
                if(space!=null){
                    space.pass(1);
                    if(space.isEnd()){
                        space = null;
                    }
                }
                if(environmentCount>0)environmentCount--;
                //回合结束

                for (int i = 0; i <1; i++) {
                    if(useSkill[i]>=0&&useSkill[i]<4){
                        useSkillOld[i] = useSkill[i];
                    }
                }
                useSkill[0]=-1;useSkill[1]=-1;

            }else {
                if(BaseFun.getPetCountInDead(FightStart.getPets1())==BaseFun.getPetsCount(FightStart.getPets1())){
                    return false;
                }
                if(BaseFun.getPetCountInDead(FightStart.getPets2())==BaseFun.getPetsCount(FightStart.getPets2())){
                    return true;
                }


                if(useSkill[0]<0&&deadFlag>0){
                    int i = deadFlag/100-1;
                    if(i>=0){
                        useSkill[0]=i;
                    }
                    Game.write(MainFrame.useSkill[0]+"");
                    deadFlag =-1;
                }

                if(useSkill[1]<0){
                    String read = Game.read();
                    //System.out.println("read:"+read);
                    if("run".equals(read)){
                        System.out.println("敌方已逃跑");
                        return true;
                    }else {
                        useSkill[1] = Integer.parseInt(read);
                    }
                }

                sleep(50);
            }

        }

        return false;



    }

    /**
     * 绑定技能栏
     */
    public static void bondSkilllabels(Pet pet){
        ArrayList<SkillInterface> skills = pet.getSkills();
        //绑定技能
        for (int i = 0; i <4; i++) {
            skillItemLabels[i] = new SkillItemLabel(i, skills.get(i));
            addCompant(MainFrame.skillItemLabels[i], 241 + 180 * i, 430, 175, 99);
        }
    }
    public static void updateSkilllabels(Pet pet){
        ArrayList<SkillInterface> skills = pet.getSkills();
        //绑定技能
        for (int i = 0; i <4; i++) {
            skillItemLabels[i].setSkill(pet.getSkills().get(i));
            skillItemLabels[i].repaint();
            //addCompant(MainFrame.skillItemLabels[i], 241 + 180 * i, 430, 175, 99);
        }
    }




    //换宠物
    public static void changePet(Pet pet,int index){
        if(pet==MainFrame.pet1){
            //处理前面的宠物  赋予下一只宠物(当前宠物pet)的增益
            if(nextPetShow1!=null) {
                nextPetShow1.init(pet);
            }
            MainFrame.pet1 = FightStart.getPets1()[index];
            initAttribute(MainFrame.pet1);
            useSkillOld[0] =  -1;

        } else if(pet==MainFrame.pet2){
            if(nextPetShow2!=null){
            nextPetShow2.init(pet);}

            MainFrame.pet2 = FightStart.getPets2()[index];
            initAttribute(MainFrame.pet2);
            useSkillOld[1] = -1;
        }

        changePet.setText("宠物");
        //hidePetLabels();






    }

    //宠物使用技能
    public static void petUseSkill(Pet resPet,int index,Pet dstPet){
        if(index>9){
            //换宠物
            changePet(resPet,index-10);
        }else {
            resPet.useSkill(index, dstPet);
        }
    }




    /**
     * 判断pet1的 的速度是否更快，  一样则随机
     * @param pet1
     * @param pet2
     * @param index     pet1释放技能的位置
     * @param index2     pet2释放技能的位置
     * @return      传入的pet1速度是否比pet2块
     */
    public static boolean moreSpeed(Pet pet1, Pet pet2,int index,int index2){

        if(index>4&&index<10)return false;        //异常状态跳过回合
        if(index2>4&&index2<10)return true;
        int speed1;
        if(index>9){
            if(pet1.getHp()<=0){
                return true;
            }
            speed1 = 3500;
        }else {
            speed1 = caculateSpeed(pet1, index) * pet1.getSpeed();
        }
        //System.out.println("MainFrame:speed1="+speed1);
        int speed2;
        if(index2>9){
            if(pet2.getHp()<=0){
                return false;
            }
            speed2 = 3500;
        }else {
            speed2 = caculateSpeed(pet2,index2)*pet2.getSpeed();
        }
        //System.out.println("MainFrame:speed2="+speed2);

        int resualt = speed1-speed2;
        if(resualt>0)return true;
        else if(resualt<0)return false;
        else {
            boolean b = BaseFun.is(50);
            if(Game.connectLeft){return b;}else {
                return !b;
            }
        }

    }


    public static int caculateSpeed(Pet pet,int index){
        int speed1 = pet.getSkills().get(index).getSpeed();
        if(pet1.getStatus("束缚")!=null){
            speed1 = speed1%2==0?speed1/2:speed1/2+1;
        }
        return speed1;
    }

    public static void initAttribute(Pet p1,Pet p2){


        bondSkilllabels(p1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                pet1 = p1;
                icon1.setIconLabel(pet1,true);
                addCompant(icon1,0,0,100,94);
                p1.getBloodLine().setAllHp(p1.getBaseHp(),p1.getHp());
                pawnRun(pet1,"show");
            }
        }).start();




        pet2 = p2;
        icon2.setIconLabel(pet2,false);
        addCompant(icon2,860,0,100,94);
        p2.getBloodLine().setAllHp(p2.getBaseHp(),p2.getHp());
        pawnRun(pet2,"show");


    }

    public static void initAttribute(Pet p1){


        if(p1==MainFrame.pet1)updateSkilllabels(p1);


        if(p1==MainFrame.pet1){
            icon1.setIconLabel(p1,true);
        }else {
            icon2.setIconLabel(p1,false);
        }
        p1.getBloodLine().setAllHp(p1.getBaseHp(),p1.getHp());
        showStatusMessage();
        pawnRun(p1,"show");


    }


    public static void addCompant(JComponent jComponent,int x,int y,int w,int h){

        jComponent.setBounds(x,y,w,h);
        jLayeredPane.add(jComponent,200);
        jLayeredPane.moveToFront(jComponent);
    }
    public static void addCompant(JComponent jComponent,int x,int y,int w,int h,int position){

        jComponent.setBounds(x,y,w,h);
        jLayeredPane.add(jComponent,200);
        jLayeredPane.moveToFront(jComponent);
        JLabel jLabel = (JLabel)jComponent;
        jLabel.setHorizontalAlignment(position);



    }

    public static void flash(){

        /*jLayeredPane.moveToFront(pawn2);
        jLayeredPane.moveToFront(pawn1);
        jLayeredPane.moveToFront(statusShowLabel);
        jLayeredPane.moveToFront(tuoShouSkillPlayer);
        jLayeredPane.moveToFront(statusLabel[0]);
        jLayeredPane.moveToFront(statusLabel[1]);
        jLayeredPane.moveToFront(bloodLine2);
        jLayeredPane.moveToFront(bloodLine1);
        jLayeredPane.moveToFront(showDamageLabel);
        jLayeredPane.moveToFront(messageLabel);
        jLayeredPane.moveToFront(skillLabel);
        for (int i = 0; i <4; i++) {
            jLayeredPane.moveToFront(skillItemLabels[i]);
        }*/

    }



    public static void hideSkillLabel(){
        for (int i = 0; i <4; i++) {
            jLayeredPane.moveToBack(skillItemLabels[i]);
        }
    }
    public static void hidePetLabels(){
        for (int i = 0; i <6; i++) {
            jLayeredPane.moveToBack(petItemLabels[i]);
        }
    }
    public static void showSkillLabel(){
        for (int i = 0; i <4; i++) {
            jLayeredPane.moveToFront(skillItemLabels[i]);
        }
    }
    public static void showPetLabels(){
        for (int i = 0; i <6; i++) {
            jLayeredPane.moveToFront(petItemLabels[i]);
        }
    }

    public MainFrame(){

        addCompant(backGround,0,0,960,540);
        addCompant(pawn2,390,0,570,430,JLabel.RIGHT);
        addCompant(pawn1,0,0,570,430,JLabel.RIGHT);
        addCompant(tuoShouSkillPlayer,0,80,960,350);
        addCompant(statusLabel[0],0,20,480,430,JLabel.LEFT);
        addCompant(statusLabel[1],480,20,480,430,JLabel.RIGHT);
        addCompant(bloodLine2,580,30,300,22);
        addCompant(bloodLine1,80,30,300,22);
        addCompant(showDamageLabel,0,0,960,270);
        addCompant(messageLabel,0,0,0,0);
        addCompant(skillLabel,0,418,960,6);
        addCompant(changePet,15,440,80,35,JLabel.CENTER);
        addCompant(runLabel,15,490,80,35,JLabel.CENTER);
        addCompant(environmentLabel,440,25,80,50);
        addCompant(statusShowLabel[0],90,50,290,40);
        addCompant(statusShowLabel[1],580,50,290,40);




        backGround.setIcon(new ImageIcon(AllConfig.rootPath+"UI/背景/background.jpg"));


        skillLabel.setIcon(new ImageIcon(AllConfig.rootPath+"UI/技能栏/src.png"));



        changePet.setFont(new Font("楷书",Font.BOLD,24));
        changePet.setBackground(new Color(9, 80, 155));
        changePet.setForeground(new Color(255,255,255));
        changePet.setCursor(new Cursor(Cursor.HAND_CURSOR));
        runLabel.setFont(new Font("楷书",Font.BOLD,24));
        runLabel.setBackground(new Color(9, 80, 155));
        runLabel.setForeground(new Color(255,255,255));
        runLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        changePet.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (MainFrame.useSkill[0] >= 0) return;
                if (pet1.getHp()<=0) return;
                if("宠物".equals(changePet.getText())){

                    if(pet1.getMark("无法换宠")!=null)return;
                    hideSkillLabel();
                    showPetLabels();

                    changePet.setText("战斗");
                }else {

                    hidePetLabels();
                    showSkillLabel();

                    changePet.setText("宠物");
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

                if (MainFrame.useSkill[0] >= 0) return;
                if (pet1.getHp()<=0) return;
                changePet.setBackground(new Color(0, 174, 207));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (MainFrame.useSkill[0] >= 0) return;
                if (pet1.getHp()<=0) return;
                changePet.setBackground(new Color(9, 80, 155));
            }
        });
        runLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                close();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

                if (MainFrame.useSkill[0] >= 0) return;
                if (pet1.getHp()<=0) return;
                runLabel.setBackground(new Color(0, 174, 207));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (MainFrame.useSkill[0] >= 0) return;
                if (pet1.getHp()<=0) return;
                runLabel.setBackground(new Color(9, 80, 155));
            }
        });
        runLabel.setOpaque(true);

        changePet.setOpaque(true);


        statusShowLabel[0].setFont(new Font("楷书",Font.ITALIC,12));
        statusShowLabel[1].setFont(new Font("楷书",Font.ITALIC,12));
        statusShowLabel[0].setForeground(new Color(255,255,255));
        statusShowLabel[1].setForeground(new Color(255,255,255));
        //statusShowLabel.setBackground(new Color(255,255,255,0));





        bloodLine1.setLeft(true);




        bloodLine2.setLeft(false);

        showDamageLabel.setFont(new Font("楷书",Font.ITALIC,80));

        messageLabel.setForeground(new Color(255,255,255));
        messageLabel.setOpaque(true);
        messageLabel.setBackground(new Color(24, 49, 76));


        environmentLabel.setForeground(new Color(255,255,255));
        environmentLabel.setFont(new Font("楷书",Font.BOLD,32));


        //拖动
        backGround.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                xOld = e.getX();
                yOld = e.getY();
            }
        });
        backGround.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                int xOnScreen = e.getXOnScreen();//鼠标的绝对坐标
                int yOnScreen = e.getYOnScreen();
                int xx = xOnScreen - xOld;
                int yy = yOnScreen - yOld;
                MainFrame.this.setLocation(xx, yy);
            }
        });


        this.add(jLayeredPane);
        this.setSize(960,540);
        this.setLocation(480,270);
        this.setUndecorated(true);
        this.setVisible(true);


    }




    public static void sleep(long l){
        try {
            Thread.sleep(l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
