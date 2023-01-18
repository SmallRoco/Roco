package content.skill.shui;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillBuQingYun implements SkillInterface {
    String name = "步青云";
    String animal = "att1";
    boolean physical = false;
    int speed = 11;
    String type = "水";
    int power = 80;
    int pp = 12;
    String description = "前程锦绣，平步青云。受福气与自身速度等级加成的强力攻击，攻击不抵抗或克制水系的目标会召唤阴雨天气，福气全部分享则在攻击抵抗或克制水系的目标时清除非阴雨与星云的天气。对已被催眠的目标有奇效。先手+3";
    @Override
    public String getAnimal() {
        return animal;
    }
    @Override public String getName() {
        return name;
    }
    @Override public boolean getPhysical() {
        return physical;
    }
    @Override public int getSpeed() {
        return speed;
    }
    @Override public String getType() {
        return type;
    }
    @Override public int getPower() {
        return power;
    }
    @Override public int getPp() {
        return pp;
    }
    @Override public String getDescription() { return description; }
    @Override public void setDescription(String description) { this.description = description; }

@Override public String getTuoshou(){return "att1";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {

        Integer fuQi = resPet.getMark("福气");
        int speedGrade = resPet.getAppendStatus()[4];
        if(fuQi!=null){
            speedGrade+=fuQi-1000;
        }
        BaseFun.att(resPet,dstPet,power+speedGrade*5,physical,type);
        if(fuQi==null&&BaseFun.attritube(dstPet.getType(),"水")==0){
            MainFrame.setEnvironment("阴雨",5);
        }
        if(fuQi==null){
            if(!MainFrame.getEnvironment().equals("阴雨")&&!MainFrame.getEnvironment().equals("星云")){
                MainFrame.setEnvironment("",0);
            }
        }
        if(dstPet.getStatus("催眠")!=null){
            dstPet.removeStatus("催眠");
        }
    }


}
