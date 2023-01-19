package content.skill.new1;

import Config.ConfigFile;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillXiSheng implements SkillInterface {
    String name = "牺牲";
    String animal = "att1";
    boolean physical = false;
    int speed = 9;
    String type = "水";
    int power = 175;
    int pp = 10;
    String description = "消耗自身部分精力值，短期内限制敌方攻击，敌方越强限制越大。先手+1";
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

@Override public String getTuoshou(){return "翅膀增益";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        BaseFun.att(resPet,dstPet,power,physical,type);
        resPet.setHp(resPet.getHp()-resPet.getBaseHp()*0.3);
        dstPet.setAttLimit(70, ConfigFile.get("短期"));
    }
}
