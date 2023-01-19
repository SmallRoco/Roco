import Config.ConfigFile;
import Config.ImageUtils;
import content.SkillManager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class Test {

    public static void main(String[] args) {


        String s = "冰冻\n" +
                "水系魔法\n" +
                "15\n" +
                "15\n" +
                "2-5回合内，敌人无法替换宠物，并持续伤血。\n" +
                "圣洁\n" +
                "水系变化\n" +
                "0\n" +
                "3\n" +
                "主动：驱散负面状态，短期内闪避提升；被动：免疫睡眠、麻醉、诅咒。先手+4\n" +
                "恩泽\n" +
                "水系变化\n" +
                "0\n" +
                "20\n" +
                "消耗自身精力治愈场下受伤最重的队友，同时提升自身防御。先手+1\n" +
                "洗礼\n" +
                "水系魔法\n" +
                "125\n" +
                "10\n" +
                "驱散敌方的增益状态，并藉此治疗自身。先手+2\n" +
                "净化\n" +
                "水系魔法\n" +
                "80\n" +
                "5\n" +
                "清除对方所有技能2点PP值，并藉此治疗自身。先手+1\n" +
                "牺牲\n" +
                "水系魔法\n" +
                "175\n" +
                "10\n" +
                "消耗自身部分精力值，短期内限制敌方攻击，敌方越强限制越大。先手+1\n" +
                "碧天清晓\n" +
                "水系变化\n" +
                "0\n" +
                "4\n" +
                "风丝扫镜，雨晴净空。被动免疫束缚、催眠、寄生、迷惑、麻醉和烧伤，并根据PP值累计层数。主动使用将归零层数，召唤阴雨并净化存活宠物的异常与负面，短暂免疫异常且根据储能层数回复精力与PP值。防控回合内减免自身所受的伤害。若储能已满，则根据净化的异常与负面项数为受净化的宠物回复PP。先手+7\n" +
                "湍玉漱石\n" +
                "水系魔法\n" +
                "100\n" +
                "10\n" +
                "片云飞瀑，裁素悬秋。降低对方一级魔抗，根据储能层数删除对方PP并增幅威力，降低魔抗失败或对方存在强化则增强删PP效果。降低魔抗失败则追加伤害，回复我方全体精力。先手+2\n" +
                "烟波长静\n" +
                "水系变化\n" +
                "0\n" +
                "10\n" +
                "薄霭云淡，收风凝川。据自身已损精力追加固伤并消除双方正面强化。消除对方成功则删除其最近使用技能PP，否则为己方精力上限最高的队友提供精力与PP回复。若对方不具有冰冻或睡眠抗性，会赋予对方场上宠物和下一只替换的宠物额外的削弱状态并回复自身部分技能PP。受到伤害时必中。后手-1\n" +
                "灵渊移星\n" +
                "冰系魔法\n" +
                "110\n" +
                "10\n" +
                "澄渊映斗，星汉西流。冰冻对方，免疫则为下一只出场宠物提供护盾，储能层数越低效果越好。自身无正面强化时额外回复碧天清晓PP。之后在非冰雹天气下提升魔攻。先手+3";




        String[] ss = s.split("\n");

        String s1 = "";
        for (int i = 0; i <ss.length; i++) {


            s1+=ss[i]+"\n";

            if((i+1)%5==0) {
                try {
                    createSkill(s1);
                    s1 = "";
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }









    }

    public static void createSkill(String s) throws UnsupportedEncodingException {


        String[] ss = s.split("\n");


        String name = ss[0];
        String type = "";
        String animal = "magic";
        int power = Integer.parseInt(ss[2]);
        int pp = Integer.parseInt(ss[3]);
        String description = ss[4];
        int speed = 8;

        boolean physical = false;


        for (int i = 0; i < ConfigFile.types.length; i++) {
            if(ss[1].contains(ConfigFile.types[i])){
                type = ConfigFile.types[i];
            }
        }

        if(description.split("先手\\+").length>1){
            speed += Integer.parseInt(description.split("先手\\+")[1]);

        }

        if(ss[1].contains("物理")){
            animal = "att1";
            physical = true;
        }
        //System.out.println("技能名称:"+name);
        if(SkillManager.getHashMap().get(name)!=null){
            System.out.println(name+"：已存在");
            return;}
        /*System.out.println("技能属性:"+type);
        System.out.println("animal:"+animal);
        System.out.println("技能威力:"+power);
        System.out.println("技能速度:"+speed);
        System.out.println("技能PP: "+pp);
        System.out.println("是否物理:"+physical);
        System.out.println("技能描述:"+description);*/


        String pinyin;
        if("赤月魅焰".equals(name)){
            pinyin = "ChiYueMeiYan";
        }else {

            System.out.println(name);
            pinyin = JString.getPinyin(name);
        }




        String className = "Skill"+pinyin;

        String url = "D:\\desktop\\java.me\\Roco\\src\\main\\java\\content\\skill\\"+"new1"+"\\"+className+".java";
        File file = new File(url);

        if(file.exists()){
            return;
        }

        String content = "package content.skill."+"new1"+";\n" +
                "\n" +
                "import content.BaseFun;\n" +
                "import content.Pet;\n" +
                "import content.SkillInterface;\n" +
                "\n" +
                "public class "+className+" implements SkillInterface {\n" +
                "    String name = \""+name+"\";\n" +
                "    String animal = \""+animal+"\";\n" +
                "    boolean physical = "+physical+";\n" +
                "    int speed = "+speed+";\n" +
                "    String type = \""+type+"\";\n" +
                "    int power = "+power+";\n" +
                "    int pp = "+pp+";\n" +
                "    String description = \""+description+"\";\n" +
                "    @Override\n" +
                "    public String getAnimal() {\n" +
                "        return animal;\n" +
                "    }\n" +
                "    @Override public String getName() {\n" +
                "        return name;\n" +
                "    }\n" +
                "    @Override public boolean getPhysical() {\n" +
                "        return physical;\n" +
                "    }\n" +
                "    @Override public int getSpeed() {\n" +
                "        return speed;\n" +
                "    }\n" +
                "    @Override public String getType() {\n" +
                "        return type;\n" +
                "    }\n" +
                "    @Override public int getPower() {\n" +
                "        return power;\n" +
                "    }\n" +
                "    @Override public int getPp() {\n" +
                "        return pp;\n" +
                "    }\n" +
                "    @Override public String getDescription() { return description; }\n" +
                "    @Override public void setDescription(String description) { this.description = description; }\n" +
                "\n";

                content+= (physical)?"":("@Override public String getTuoshou(){return \"魔焰瞬击\";};\n");
                content += "    @Override\n" +
                "    public void skill(Pet resPet, Pet dstPet) {\n";

                if(power>0){
                    content+="BaseFun.att(resPet,dstPet,power,physical,type);"+"\n";
                }


                content += "    }\n" +
                "}\n";


        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //快捷创建技能
    }
}
