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

    //从百度百科复制下来的技能词条直接赋值给s 生成技能java文件
    public static void main(String[] args) {


        String s = "雪隐\n" +
                "冰系变化\n" +
                "0\n" +
                "4\n" +
                "召唤冰雹天气袭击场上对手，五回合内对场上非水、冰、神水系宠物造成伤害，短时间内降低对方的攻击力。先手+4\n" +
                "纯白\n" +
                "冰系变化\n" +
                "0\n" +
                "6\n" +
                "以纯白雾气保护自身，重置并暂时锁定敌我双方的属性能力等级，换宠则效果失效。先手+2\n" +
                "水汽凝结\n" +
                "冰系魔法\n" +
                "100\n" +
                "15\n" +
                "有概率冻结敌人，对水和神水系敌人效果拔群。先手+1\n" +
                "寒冰裁决\n" +
                "冰系魔法\n" +
                "100\n" +
                "20\n" +
                "高命中技能，会暂时以寒气削减对方的攻击力。先手+1\n" +
                "鼓舞\n" +
                "水系变化\n" +
                "0\n" +
                "5\n" +
                "为己方全员提供精力值回复，还会随机强化自身。先手+1\n" +
                "寒霜涅槃\n" +
                "冰系变化\n" +
                "0\n" +
                "5\n" +
                "3回合内每回合恢复50点HP，回合内若我方miss对手的攻击，则对手会被冰冻。\n" +
                "寒冰\n" +
                "冰系魔法\n" +
                "120\n" +
                "10\n" +
                "以绝对零度的寒流冰冻对手，对面免疫则会吸取PP值。如果对方已经处于冰冻状态，则对方下一个上场宠物会进入冰冻状态。后手-1\n" +
                "凝霜\n" +
                "冰系变化\n" +
                "0\n" +
                "4\n" +
                "凝聚冰霜保护自身，暂时免疫异常状态，五回合内持续恢复生命，降低自身受到的伤害，期间受击会冻结对手。先手+7\n" +
                "冰天雪地\n" +
                "冰系变化\n" +
                "0\n" +
                "10\n" +
                "6回合内，双方都受到伤害。对冰系宠物无效。对方换宠依然有效。先手+1";




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
        //编码问题找不到拼音的 要手动添加拼音
        if("寒霜涅槃".equals(name)){
            pinyin = "HanShuangNiePan";
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
