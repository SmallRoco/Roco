package content.skill.emo;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillKuangYao implements SkillInterface {
    String name = "狂咬";
    String animal = "att1";
    boolean physical = true;
    int speed = 8;
    String type = "恶魔";
    int power = 60;
    int pp = 25;
    String description = "一定机率让对手恐惧。";
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
        if(BaseFun.is(55)){
            resPet.addStatus("恐惧");
        }
BaseFun.att(resPet,dstPet,power,physical,type);
    }
}
