package content.skill.shui;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillKuangBaoShuiYuanSu implements SkillInterface {
    String name = "狂暴水元素";
    String animal = "att1";
    boolean physical = true;
    int speed = 8;
    String type = "水";
    int power = 100;
    int pp = 5;
    String description = "给对方造成一定的伤害。";
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
BaseFun.att(resPet,dstPet,power+30,physical,type);
    }
}
