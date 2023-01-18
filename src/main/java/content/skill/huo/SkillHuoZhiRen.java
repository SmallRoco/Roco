package content.skill.huo;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillHuoZhiRen implements SkillInterface {
    String name = "火之刃";
    String animal = "att1";
    boolean physical = true;
    int speed = 8;
    String type = "火";
    int power = 65;
    int pp = 15;
    String description = "一定机率让对进入烧伤状态或恐惧状态，两效果不可同时出现。";
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
        BaseFun.att(resPet,dstPet,power,physical,type);
        if(BaseFun.is(50)){
            dstPet.addStatus("烧伤");
        }else if(BaseFun.is(40)){
            dstPet.addStatus("恐惧");
        }
    }
}
