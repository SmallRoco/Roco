package content.skill.huo;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillJuYanTunShi implements SkillInterface {
    String name = "巨焰吞噬";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "火";
    int power = 110;
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

@Override public String getTuoshou(){return "魔焰瞬击";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
BaseFun.att(resPet,dstPet,power,physical,type);
    }
}
