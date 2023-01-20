package content.skill.guang;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillPoMoShengGuang implements SkillInterface {
    String name = "破魔圣光";
    String animal = "att1";
    boolean physical = false;
    int speed = 9;
    String type = "光";
    int power = 125;
    int pp = 10;
    String description = "先净化掉对方的防御强化，再造成巨大的伤害。先手+1";
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
        int appendStatus = dstPet.getAppendStatus()[2];
        if(appendStatus>0){
            dstPet.statusChange(2,appendStatus);
        }
        BaseFun.att(resPet,dstPet,power,physical,type);
    }
}
