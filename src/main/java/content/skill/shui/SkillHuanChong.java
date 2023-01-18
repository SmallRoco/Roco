package content.skill.shui;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillHuanChong implements SkillInterface {
    String name = "缓冲";
    String animal = "att1";
    boolean physical = true;
    int speed = 8;
    String type = "水";
    int power = 80;
    int pp = 10;
    String description = "一定概率提升自身防御等级。";
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

    @Override
    public void skill(Pet resPet, Pet dstPet) {
        if(BaseFun.is(60)){
            resPet.statusChange(3,1);
        }

        BaseFun.att(resPet,dstPet,power,physical,type);
    }
}
