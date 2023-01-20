package content.skill.guang;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillPoMoZhiGuang implements SkillInterface {
    String name = "破魔之光";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "光";
    int power = 100;
    int pp = 10;
    String description = "给对手造成一定伤害。如果对手是火魔会造成更高的伤害。";
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

@Override public String getTuoshou(){return "火眼金睛";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {

        if(dstPet.getName().equals("火魔")){
            BaseFun.trueDamage(resPet,dstPet,250);
        }else {
            BaseFun.trueDamage(resPet,dstPet,200);
        }
    }
}
