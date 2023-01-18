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


        String s = "苍龙追月\n" +
                "武系物理\n" +
                "120\n" +
                "12\n" +
                "苍云追月式，造成物理伤害同时，提升自身1级物攻和1级速度等级。先手+1\n" +
                "万夫莫敌\n" +
                "武系物理\n" +
                "100\n" +
                "12\n" +
                "提升一级物攻与命中等级，再对敌方造成巨大伤害。先手+2\n" +
                "龙战于野\n" +
                "武系变化\n" +
                "0\n" +
                "6\n" +
                "净化自身解除所有异常状态与负面强化，提升自身1级闪避等级和1级命中等级，暂时免疫异常状态与负面强化，受苍龙庇佑，4回合内每次攻击回复75生命值。先手+4\n" +
                "真龙乱舞\n" +
                "龙系物理\n" +
                "100\n" +
                "10\n" +
                "连续三回合对敌方造成伤害。\n" +
                "虚诱掩杀\n" +
                "武系物理\n" +
                "80\n" +
                "6\n" +
                "提升自身闪避并造成高额伤害。先手+4\n" +
                "蔚蓝龙息\n" +
                "龙系魔法\n" +
                "100\n" +
                "5\n" +
                "含有闪电与霹雳的龙息，有几率麻醉敌人。先手+1\n" +
                "灵域风暴\n" +
                "幽灵魔法\n" +
                "100\n" +
                "10\n" +
                "如果本轮先手，则提升魔攻2级。如果本轮后手，则提升速度1级。\n" +
                "天若有情\n" +
                "武系物理\n" +
                "125\n" +
                "10\n" +
                "造成两段伤害，或是回复自身部分生命值。先手+1\n" +
                "一骑讨\n" +
                "武系物理\n" +
                "90\n" +
                "8\n" +
                "造成物理伤害同时使敌方虚弱，并强制对手4回合内无法更换宠物。先手+2\n" +
                "龙之传承\n" +
                "龙系变化\n" +
                "0\n" +
                "3\n" +
                "解除异常状态并暂时免疫异常状态与负面强化，立即强化自身并恢复部分生命。先手+4";




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
