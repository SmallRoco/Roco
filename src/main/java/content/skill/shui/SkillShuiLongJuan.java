package content.skill.shui;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillShuiLongJuan implements SkillInterface {
    String name = "水龙卷";
    String animal = "magic";
    boolean physical = false;
    int speed = 9;
    String type = "水";
    int power = 120;
    int pp = 5;
    String description = "召唤一阵雷暴雨，使场上六回合内变为阴雨天气。先手+1";
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

@Override public String getTuoshou(){return "冰天雪地";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        MainFrame.setEnvironment("阴雨",6);
        BaseFun.att(resPet,dstPet,power,physical,type);
    }
}
