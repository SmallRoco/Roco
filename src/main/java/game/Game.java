package game;

import Config.Action;
import Config.AttributeAdd;
import Config.ConfigFile;
import UI.BloodLine;
import UI.MainFrame;
import Utils.BaseFunction;
import content.*;
import game.ui.GameMainF;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Consumer;

public class Game {

    public static HashMap<String,Boolean> myPetS = new HashMap<>();

    private static Socket socket;
    private static ObjectInputStream objectInputStream = null;
    private static ObjectOutputStream objectOutputStream = null;
    public static boolean connectFlag =false;
    public static boolean connectLeft = false;

    private static Pet[] pets1 = new Pet[6];
    private static Pet[] pets2 = new Pet[6];

    public static GameMainF gameMainF;

    public static Pet[] getPets1() {
        return pets1;
    }

    public static void main(String[] args) {



        /*pets1[0] = new Pet("烈火战神",10,185,750,345,290,352,356,324,new String[]{"水之护盾","空气切割","热力爆弹","魔焰瞬击"}, MainFrame.pawn1,MainFrame.bloodLine1);
        pets1[1] = new Pet("圣灵草王",900,185,750,345,290,352,356,324,new String[]{"圣腾控制","自然之友","灵蔓盾击","召唤草灵"}, MainFrame.pawn1,MainFrame.bloodLine1);

        pets2[0] = new Pet("大寒",900,185,750,345,290,352,356,324,new String[]{"今朝醉","液态水环","窒息","水之波纹"}, MainFrame.pawn2,MainFrame.bloodLine2);
        */

        loadFromFile();
        loadMyPetsFromFile();

        Scanner scanner = new Scanner(System.in);

        /*for (int i = 0; i < ConfigFile.PetNames.length; i++) {
            getPet(ConfigFile.PetNames[i]);
        }
*/

        gameMainF = new GameMainF();


        //getPet("福熙水灵");
        //联网对战 666

        whe:while (true){

            String s = scanner.nextLine();
            s+=" ";
            String[] cmd = s.split(" ");
            if(cmd.length==0)continue;
            if("联网对战".equals(cmd[0])){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            socket = new Socket("123.60.73.77",8082);
                            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                            objectInputStream  = new ObjectInputStream(socket.getInputStream());

                            write("enterBattle:"+cmd[1]);
                            connectFlag = true;

                            write("connected");

                            String read = read();
                            //System.out.println("Game:"+read);
                            if(read==null){
                                connectClose();
                                return;
                            }

                            if("fill".equals(read)){
                                System.out.println("该房间已开战");
                                connectClose();
                                return;
                            }
                            if(read.equals("battleStart")){
                                write(petsToString(pets1));
                                String petString = read();
                                if(petString==null){connectClose();;return;};
                                stringToPets(petString,pets2,MainFrame.pawn2,MainFrame.bloodLine2);
                                String index = read();
                                if(index==null){
                                    connectClose();
                                    return;
                                }
                                if("1".equals(index)){
                                    connectLeft = false;
                                }else {
                                    connectLeft = true;
                                }

                                String random = read();
                                if(random==null){
                                    connectClose();
                                    return;
                                }
                                BaseFun.random = random.getBytes(StandardCharsets.UTF_8);
                                boolean start = FightStart.start(pets1, pets2, true);
                                //System.out.println("游戏胜利："+start);
                                loadMyPetsFromFile();
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();



            }else if("start".equals(cmd[0])){

            }else if("查看所有宠物".equals(cmd[0])){
                Set<String> strings = ConfigFile.petTypes.keySet();
                System.out.println("|     名称     |     属性     |");
                strings.forEach(
                        new Consumer<String>() {
                            @Override
                            public void accept(String s) {
                                String s1 = ConfigFile.petTypes.get(s);
                                System.out.println(" "+s+getSpace(16-(int)(s.length()*2))+s1);
                            }
                        }
                );

            }else if("设置属性".equals(cmd[0])){
                if(cmd.length==1){
                    System.out.println("请选择宠物");
                    continue;
                }
                AttributeAdd petAttributeAdd = ConfigFile.getPetAttributeAdd(cmd[1]);
                if(petAttributeAdd==null){
                    System.out.println("没有这只宠物");
                    continue;
                }
                int[] data = petAttributeAdd.getData();

                System.out.println("当前加成:");
                System.out.println("|  精力  |  物攻  |  魔攻  |  防御  |  魔抗  |  速度  |  暴击  |  闪避  |");
                for (int i = 0; i <8; i++) {
                    System.out.print(" "+data[i]+getSpace(9-(" "+data[i]).length()));
                }
                System.out.println();

                int[] ints = new int[8];
                int all = 0;
                for (int i = 0; i <8; i++) {
                    ints[i] = scanner.nextInt();
                    all+=ints[i];
                }

                if(all>800){
                    System.out.println("数据超限！");
                    continue;
                }

                System.out.println("设置成功.当前加成:");
                System.out.println("|  精力  |  物攻  |  魔攻  |  防御  |  魔抗  |  速度  |  暴击  |  闪避  |");
                for (int i = 0; i <8; i++) {
                    System.out.print(" "+ints[i]+getSpace(9-(" "+ints[i]).length()));
                }
                System.out.println();

                petAttributeAdd.setData(ints);
                updateBoxAttribute(cmd[1],ints);
                saveToFile();



            }
            else if("查看我的宠物".equals(cmd[0])){
                Set<String> strings = myPetS.keySet();
                System.out.println("|     名称     |     属性     |");
                strings.forEach(
                        new Consumer<String>() {
                            @Override
                            public void accept(String name) {
                                String s1 = ConfigFile.petTypes.get(name);
                                System.out.println(" "+name+getSpace(16-(int)(name.length()*2))+s1);
                            }
                        }
                );

            }
            else if("挑战".equals(cmd[0])){


                if(cmd.length==1){
                    System.out.println("请输入要挑战的宠物名");
                    continue ;
                }

                String name = cmd[1];
                AttributeAdd petAttributeAdd = ConfigFile.getPetAttributeAdd(name);
                AiInterface ai = AiManager.getAi(name);

                if(petAttributeAdd==null){
                    System.out.println("暂无该宠物");
                    continue ;
                }else if(ai==null){
                    System.out.println("该宠物暂未写ai 请挑选别的宠物");
                    continue ;
                }

                loadMyPetsFromFile();

                int[] data = new int[8];
                for (int i = 0; i <8; i++) {
                    data[i] = 400;
                }


                pets2[0] = Pet.creatPet(name,data,ai.getSkllNames(),MainFrame.pawn2,MainFrame.bloodLine2, AiManager.getAi("基础AI"));

                data[0] += 1000;
                data[3] += 100;
                data[4] += 100;

                pets2[1] = Pet.creatPet(name,data,ai.getSkllNames(),MainFrame.pawn2,MainFrame.bloodLine2, AiManager.getAi(name));
                data[0] += 2000;
                data[1] += 100;
                data[2] += 100;
                data[3] += 100;
                data[4] += 100;

                pets2[2] = Pet.creatPet(name,data,ai.getSkllNames(),MainFrame.pawn2,MainFrame.bloodLine2, AiManager.getAi(name));

                boolean start = FightStart.start(pets1, pets2, false);
                if(start){
                    getPet(name);
                }
            }else if("查看技能".equals(cmd[0])){
                if(cmd.length==1){
                    System.out.println("请输入技能名");
                }
                SkillInterface skillInterface = SkillManager.getHashMap().get(cmd[1]);
                if(skillInterface==null){
                    System.out.println("技能不存在");
                    continue;
                }

                System.out.println("技能名称："+skillInterface.getName());
                System.out.println("技能属性："+skillInterface.getType());
                System.out.println("技能威力："+skillInterface.getPower());
                System.out.println("技能PP： "+skillInterface.getPp());
                System.out.println("技能速度："+skillInterface.getSpeed());
                System.out.println("技能描述："+skillInterface.getDescription());
            }else if("查看背包".equals(cmd[0])){
                if(cmd.length==1){
                    System.out.println("|     名称     |     属性     |");
                    for (int i = 0; i <pets1.length; i++) {
                        if(pets1[i]==null)break;
                        String name = pets1[i].getName();
                        String s1 = ConfigFile.petTypes.get(name);
                        System.out.println(" "+name+getSpace(16-(int)(name.length()*2))+s1);
                    }
                }

            }else if("设置背包".equals(cmd[0])){
                if(cmd.length==1){
                    System.out.print("当前背包：");
                    for (int i = 0; i <pets1.length; i++) {
                        if(pets1[i]==null)break;
                        String name = pets1[i].getName();
                        System.out.print(" "+name);
                    }
                }else{

                    String[] petNames =new String[6];
                    int len=0;
                    TreeSet<String> treeSet = new TreeSet<>();
                    for (int i = 0; i <cmd.length-1; i++) {
                        petNames[i] = cmd[i+1];
                        if(cmd[i+1]!=null){
                            len++;
                            treeSet.add(petNames[i]);
                        }
                    }
                    System.out.println();
                    if(treeSet.size()<len){
                        System.out.println("不可携带重复宠物");
                        continue;
                    }


                    for (int j = 0; j <6; j++) {
                        if(petNames[j]==null)break;
                        if(myPetS.get(petNames[j])==null){
                            System.out.println("暂无该宠物："+petNames[j]);
                        }
                    }

                    addPetToBox(petNames,true);



                }

            }else {
                if(ConfigFile.getType(cmd[0])!=null){

                    if(cmd.length>1){

                        String[] skillNames = new String[4];
                        int len = 0;
                        TreeSet<String> treeSet = new TreeSet<>();
                        for (int i = 0; i <cmd.length-1; i++) {
                            skillNames[i] = cmd[i+1];
                            if(cmd[i+1]!=null){
                                len++;
                                treeSet.add(skillNames[i]);
                            }
                        }

                        if(treeSet.size()<len){
                            System.out.println("不可有重复技能");
                            continue;
                        }

                        String[] canSkills = ConfigFile.petCanSkills.get(cmd[0]);



                        for (int j = 0; j <4; j++) {
                            if(skillNames[j]==null)break;
                            for (int k = 0; k <canSkills.length; k++) {
                                if(canSkills[k].equals(skillNames[j])){
                                    break;
                                }
                                if(k==canSkills.length-1){
                                    System.out.println("没有该技能:"+skillNames[j]);
                                    printSkills(ConfigFile.petCanSkills.get(cmd[0]));
                                    continue whe;
                                }
                            }
                        }

                        updateBoxSkill(cmd[0],skillNames);
                        ConfigFile.getPetAttributeAdd(cmd[0]).setSkillName(skillNames);
                        saveToFile();



                    }
                    System.out.println(cmd[0]+"当前属性:");
                    int[] data = ConfigFile.getPetAttributeAdd(cmd[0]).getData();
                    System.out.println("|  精力  |  物攻  |  魔攻  |  防御  |  魔抗  |  速度  |  暴击  |  闪避  |");
                    for (int i = 0; i <8; i++) {
                        System.out.print(" "+data[i]+getSpace(9-(" "+data[i]).length()));
                    }
                    System.out.println();
                    System.out.print("当前技能: ");

                    String[] skillNames = ConfigFile.getPetAttributeAdd(cmd[0]).getSkillName();
                    for (int i = 0; i < skillNames.length; i++) {
                        System.out.print(" "+skillNames[i]);
                    }
                    System.out.println();


                }
            }



        }

    }

    /**
     * 设置宠物属性
     * @param name
     * @param ints
     * @return  1代表成功 -1代表失败 数据异常
     */
    public static int setAttributes(String name,int[] ints){

        AttributeAdd petAttributeAdd = ConfigFile.getPetAttributeAdd(name);
        int all = 0;
        for (int i = 0; i <8; i++) {
            all+=ints[i];
        }

        if(all>800){
            return -1;
        }

        /*System.out.println("设置成功.当前加成:");
        System.out.println("|  精力  |  物攻  |  魔攻  |  防御  |  魔抗  |  速度  |  暴击  |  闪避  |");
        for (int i = 0; i <8; i++) {
            System.out.print(" "+ints[i]+getSpace(9-(" "+ints[i]).length()));
        }
        System.out.println();*/

        petAttributeAdd.setData(ints);
        updateBoxAttribute(name,ints);
        saveToFile();
        return 1;
    }

    /**
     * 设置宠物为背包首选
     */
    public static boolean setBoxFirst(String name){

        Pet[] pets = new Pet[6];
        int count = 0;
        for (int i = 0; i <pets1.length; i++) {
            if(pets1[i]==null)break;
            if(pets1[i].getName().equals(name)){
                count=1;
            }
        }
        if(count==0)return false;
        int index = 0;
        for (int i = 0; i <pets1.length; i++) {
            if(pets1[i]==null)break;
            if(pets1[i].getName().equals(name)){
                index = i;
            }else {
                pets[count++] = pets1[i];
            }
        }
        pets[0] = pets1[index];
        pets1 = pets;
        saveMyPetsToFile();
        return true;
    }

    /**
     * 挑战
     * @return
     */
    public static void chanllage(String dstName){

        String name = dstName;
        AiInterface ai = AiManager.getAi(name);

        loadMyPetsFromFile();

        int[] data = new int[8];
        for (int i = 0; i <8; i++) {
            data[i] = 400;
        }

        pets2[0] = Pet.creatPet(name,data,ai.getSkllNames(),MainFrame.pawn2,MainFrame.bloodLine2, AiManager.getAi("基础AI"));

        data[0] += 500;
        data[1] += 100;     //双攻加100
        data[2] += 100;
        data[3] += 400;
        data[4] += 400;

        pets2[1] = Pet.creatPet(name,data,ai.getSkllNames(),MainFrame.pawn2,MainFrame.bloodLine2, AiManager.getAi(name));
        data[0] += 1000;
        data[1] += 150;     //双攻加100
        data[2] += 150;
        data[3] += 400;        //双抗加100
        data[4] += 400;

        pets2[2] = Pet.creatPet(name,data,ai.getSkllNames(),MainFrame.pawn2,MainFrame.bloodLine2, AiManager.getAi(name));

        boolean start = FightStart.start(pets1, pets2, false);
        if(start){
            getPet(name);
        }
    }

    /**
     * 设置宠物携带的技能
     * @param name
     * @param skillNames
     * @return  100代表成功  -1 代表有重复技能  0-4 代表有错技能
     */
    public static int setSkills(String name,String[] skillNames){
        int len = 0;
        TreeSet<String> treeSet = new TreeSet<>();
        for (int i = 0; i <4; i++) {
            if(skillNames[i]!=null&&!skillNames[i].equals("")){
                len++;
                treeSet.add(skillNames[i]);
            }
        }

        if(treeSet.size()<len){
            //System.out.println("不可有重复技能");
            return -1;
        }

        String[] canSkills = ConfigFile.petCanSkills.get(name);



        for (int j = 0; j <4; j++) {
            if(skillNames[j]==null)break;
            for (int k = 0; k <canSkills.length; k++) {
                if(canSkills[k].equals(skillNames[j])){
                    break;
                }
                if(k==canSkills.length-1){
                    //System.out.println("没有该技能:"+skillNames[j]);
                    //printSkills(ConfigFile.petCanSkills.get(name));
                    return j;
                }
            }
        }

        updateBoxSkill(name,skillNames);
        ConfigFile.getPetAttributeAdd(name).setSkillName(skillNames);
        saveToFile();
        return 100;
    }

    /**
     * 更新背包宠物属性
     * @param name
     */
    public static void updateBoxAttribute(String name,int[] ints){
        for (int i = 0; i <6;i++) {
            if(pets1[i]==null)return;
            if(pets1[i].getName().equals(name)){
                pets1[i].setBaseHp(300+ints[0]);
                pets1[i].setBasePhysicalAttack(300+ints[1]);
                pets1[i].setBaseMagicAttack(300+ints[2]);
                pets1[i].setBasePhysicalDefense(300+ints[3]);
                pets1[i].setBaseMagicDefense(300+ints[4]);
                pets1[i].setBaseSpeed(300+ints[5]);
                pets1[i].setBaseMiss(300+ints[6]);
                pets1[i].setBaseHitRate(300+ints[7]);

            }
        }
    }

    //更新背包宠物技能
    public static void updateBoxSkill(String name,String[] skillNames){
        for (int i = 0; i <6;i++) {
            if(pets1[i]==null)return;
            if(pets1[i].getName().equals(name)){
                pets1[i].resetSkill(skillNames);
            }
        }
    }

    //魔焰瞬击 火烧眉毛 热力爆弹 爆裂灼烧
    public static void printSkills(String[] skillNames){
        System.out.println("|  技能名称  |  技能属性  |  技能威力  |  使用次数  |  技能速度  |  技能描述  ");
        for (int i = 0; i <skillNames.length; i++) {
            SkillInterface skillInterface = SkillManager.getHashMap().get(skillNames[i]);
            System.out.print(" "+skillInterface.getName()+getSpace(12-skillInterface.getName().length()*2));
            System.out.print(" "+skillInterface.getType()+getSpace(12-skillInterface.getType().length()*2));
            System.out.print(" "+skillInterface.getPower()+getSpace(12-(skillInterface.getPower()+"").length()));
            System.out.print(" "+skillInterface.getPp()+getSpace(12-(skillInterface.getPp()+"").length()));
            System.out.print(" "+skillInterface.getSpeed()+getSpace(12-(skillInterface.getSpeed()+"").length()));
            System.out.println(" "+skillInterface.getDescription());


        }
    }

    public static void getPet(String name){
        if(myPetS.get(name)!=null) shouImportant("已拥有该宠物");
        myPetS.put(name,true);

        shouImportant("获得新宠物："+name);
        saveMyPetsToFile();
    }

    public static void shouImportant(String s){
        System.out.println("------------------------------------");
        System.out.println(s);
        System.out.println("------------------------------------");
    }




    public static void loadMyPetsFromFile(){

        byte[] bytes = BaseFunction.readOrWriteBytesFromFile("./pets.pet",null);

        if(bytes==null){

            myPetS.put("烈火战神",true);
            BaseFunction.readOrWriteBytesFromFile("./pets.pet",addS("烈火战神-烈火战神"));
            addPetToBox(new String[]{"烈火战神"},true);

        }else {
            String s = deleteS(bytes);
            String[] split = s.split("-");
            String[] s1 = split[0].split(" ");
            String[] petBox = split[1].split(" ");

            for (int i = 0; i <s1.length; i++) {

                myPetS.put(s1[i],true);
            }

            addPetToBoxNotSave(petBox,true);

        }

    }
    public static void addPetToBoxNotSave(String[] names,boolean left){
        for (int i = 0; i <names.length; i++) {
            if(names[i]==null){pets1[i] =null ;continue;}
            AttributeAdd petAttributeAdd = ConfigFile.getPetAttributeAdd(names[i]);
            int[] data = petAttributeAdd.getData();
            if(left){
                pets1[i] = new Pet(names[i], 300 + data[0], 300 + data[1], 300 + data[2], 300 + data[3], 300 + data[4], 300 + data[5], 300 + data[6], 300 + data[7], petAttributeAdd.getSkillName(), MainFrame.pawn1, MainFrame.bloodLine1);

            }else {
                pets2[i] =  new Pet(names[i], 300 + data[0], 300 + data[1], 300 + data[2], 300 + data[3], 300 + data[4], 300 + data[5], 300 + data[6], 300 + data[7], petAttributeAdd.getSkillName(), MainFrame.pawn2, MainFrame.bloodLine2);

            }
        }
    }

    public static void addPetToBox(String[] names,boolean left){
        for (int i = 0; i <names.length; i++) {
            if(names[i]==null){pets1[i] =null ;continue;}
            AttributeAdd petAttributeAdd = ConfigFile.getPetAttributeAdd(names[i]);
            int[] data = petAttributeAdd.getData();
            if(left){
                pets1[i] = new Pet(names[i], 300 + data[0], 300 + data[1], 300 + data[2], 300 + data[3], 300 + data[4], 300 + data[5], 300 + data[6], 300 + data[7], petAttributeAdd.getSkillName(), MainFrame.pawn1, MainFrame.bloodLine1);

            }else {
                pets2[i] =  new Pet(names[i], 300 + data[0], 300 + data[1], 300 + data[2], 300 + data[3], 300 + data[4], 300 + data[5], 300 + data[6], 300 + data[7], petAttributeAdd.getSkillName(), MainFrame.pawn2, MainFrame.bloodLine2);

            }
        }

        saveMyPetsToFile();

    }



    public static void saveMyPetsToFile(){

        StringBuilder stringBuilder =new StringBuilder();
        myPetS.keySet().forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                stringBuilder.append(s).append(" ");
            }
        });
        stringBuilder.append("-");
        for (int i = 0; i <pets1.length; i++) {
            if(pets1[i]==null)break;
            stringBuilder.append(pets1[i].getName()+" ");
        }
        BaseFunction.readOrWriteBytesFromFile("./pets.pet",addS(stringBuilder.toString()));

    }

    public static void loadFromFile(){

        byte[] bytes = BaseFunction.readOrWriteBytesFromFile("./config.oco", null);
        ArrayList<String> petNames = ConfigFile.petIdName;
        if(bytes==null){

            StringBuilder stringBuilder = new StringBuilder("");
            for (String petName : petNames) {
                int[] ints = new int[8];
                Arrays.fill(ints, 100);
                String[] strings = ConfigFile.petCanSkills.get(petName);
                String[] skillNames = new String[4];
                for (int i = 0; i <4; i++) {
                    skillNames[i] =strings[i];
                }
                ConfigFile.setPetAttriubuteAdd(petName, new AttributeAdd(ints,skillNames));
                stringBuilder.append("100 100 100 100 100 100 100 100:").append(skillNames[0]).append(" ").append(skillNames[1]).append(" ").append(skillNames[2]).append(" ").append(skillNames[3]).append("-");
            }

            String string = stringBuilder.toString();
            BaseFunction.readOrWriteBytesFromFile("./config.oco",addS(string));

        }else {
            String s = deleteS(bytes);
            String[] split = s.split("-");
            int len = Math.min(petNames.size(),split.length);
            for (int i = 0; i < len; i++) {

                String[] attriAndSkills = split[i].split(":");
                String[] attri = attriAndSkills[0].split(" ");
                String[] skill = attriAndSkills[1].split(" ");

                int[] ints = new int[8];
                int all = 0;
                for (int j = 0; j <8; j++) {
                    ints[j] = Integer.parseInt(attri[j]);
                    all+=ints[j];
                }

                if(all>800){
                    //存档被改过
                    System.out.println("数据异常");
                    System.exit(0);
                }
                String[] canSkills = ConfigFile.petCanSkills.get(petNames.get(i));
                for (int j = 0; j <4; j++) {
                    if(skill[j].equals("null"))continue;
                    for (int k = 0; k <canSkills.length; k++) {

                        if(canSkills[k].equals(skill[j])){
                            break;
                        }
                        //存档被改过
                        if(k==canSkills.length-1){
                            System.out.println("数据异常");
                            System.exit(0);
                        }
                    }
                }
                ConfigFile.setPetAttriubuteAdd(petNames.get(i),new AttributeAdd(ints,skill));
            }

            //有新宠物
            if(len<petNames.size()){
                for (int i = len; i <petNames.size(); i++) {
                    int[] ints = new int[8];
                    Arrays.fill(ints, 100);
                    String[] strings = ConfigFile.petCanSkills.get(petNames.get(i));
                    String[] skillNames = new String[4];
                    for (int j = 0; j <4; j++) {
                        skillNames[j] =strings[j];
                    }
                    ConfigFile.setPetAttriubuteAdd(petNames.get(i), new AttributeAdd(ints,skillNames));
                }
            }
            saveToFile();

        }

    }

    public static void saveToFile(){

        ArrayList<String> petNames = ConfigFile.petIdName;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < petNames.size(); i++) {
            AttributeAdd petAttributeAdd = ConfigFile.getPetAttributeAdd(petNames.get(i));
            int[] ints = petAttributeAdd.getData();
            String[] skillNames = petAttributeAdd.getSkillName();

            for (int j = 0; j <8; j++) {
                if(j<7) stringBuilder.append(ints[j]).append(" ");
                else stringBuilder.append(ints[j]).append(":");
            }
            stringBuilder.append(skillNames[0]).append(" ").append(skillNames[1]).append(" ").append(skillNames[2]).append(" ").append(skillNames[3]).append("-");
        }



        BaseFunction.readOrWriteBytesFromFile("./config.oco",addS(stringBuilder.toString()));
    }

    public static byte[] addS(String s){
        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        for (int i = 0; i < bytes.length; i++) {
            bytes[i]+=111;
        }
        return bytes;
    }
    public static String deleteS(byte[] bytes){
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] -= 111;
        }
        return new String(bytes,StandardCharsets.UTF_8);
    }

    public static String getSpace(int i){
        StringBuilder stringBuilder = new StringBuilder(" ");
        for (int j = 1; j <i; j++) {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }





    public static void connectClose(){

        try {
            objectInputStream.close();
            objectOutputStream.close();
            socket.close();;
            objectOutputStream = null;
            objectInputStream = null;
            socket = null;

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            connectFlag = false;
        }


    }

    public static void write(String s){
        if(objectOutputStream!=null){
            try {
                objectOutputStream.writeObject(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static String read(){
        if(objectInputStream!=null){
            try {
                return (String) objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public static void stringToPets(String s, Pet[] pets, JLabel pawn, BloodLine bloodLine){
        String[] split = s.split("-");
        for (int i = 0; i <split.length; i++) {
            String petS = split[i];
            String[] split1 = petS.split(":");
            //前几个是name 和 技能
            String nameAndSkills = split1[0];
            String attribute = split1[1];

            String[] nas = nameAndSkills.split(" ");
            String[] ab = attribute.split(" ");
            int[] abs = new int[8];
            for (int j = 0; j <8; j++) {
                abs[j] = Integer.parseInt(ab[j]);
            }
            String[] strings = new String[4];
            for (int j = 0; j <4; j++) {
                if(nas.length-1<j+1){
                    strings[j] = null;
                }else {
                    strings[j] = nas[j+1];
                }
            }

            pets[i] = new Pet(nas[0],abs[0],abs[1],abs[2],abs[3],abs[4],abs[5],abs[6],abs[7],strings,pawn,bloodLine);

        }
    }
    public static String petsToString(Pet[] pets){

        StringBuilder stringBuilder = new StringBuilder("");

        for (int i = 0; i <pets.length; i++) {
            Pet pet = pets[i];

            if(pet==null)return stringBuilder.toString();

            stringBuilder.append(pet.getName());
            ArrayList<SkillInterface> skills = pet.getSkills();
            for (int j = 0; j <skills.size(); j++) {
                if(skills.get(j)==null)break;
                stringBuilder.append(" "+skills.get(j).getName());
            }

            stringBuilder.append(":");
            stringBuilder.append(pet.getBaseHp());
            stringBuilder.append(" ").append(pet.getBasePhysicalAttack());
            stringBuilder.append(" ").append(pet.getBaseMagicAttack());
            stringBuilder.append(" ").append(pet.getBasePhysicalDefense());
            stringBuilder.append(" ").append(pet.getBaseMagicDefense());
            stringBuilder.append(" ").append(pet.getBaseSpeed());
            stringBuilder.append(" ").append(pet.getBaseBoom());
            stringBuilder.append(" ").append(pet.getBaseMiss());

            stringBuilder.append("-");


        }
        return stringBuilder.toString();

    }
}
