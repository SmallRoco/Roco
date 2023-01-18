package content.skill.shui;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillJingHua implements SkillInterface {
    String name = "净化";
    String animal = "att1";
    boolean physical = false;
    int speed = 9;
    String type = "水";
    int power = 80;
    int pp = 5;
    String description = "清除对方所有技能2点PP值，并藉此治疗自身。先手+1";
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

    @Override public String getTuoshou(){return "魔焰瞬击";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        BaseFun.att(resPet,dstPet,power,physical,type);
        BaseFun.allPpChange(dstPet,-2);
        resPet.setHp(resPet.getHp()*0.2);
    }
}
