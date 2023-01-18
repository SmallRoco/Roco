package content.skill.Long;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillLongZhiZhuaYa implements SkillInterface {
    String name = "龙之爪牙";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "龙";
    int power = 90;
    int pp = 10;
    String description = "给对手造成一定伤害。";
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


@Override public String getTuoshou(){return "龙之爪牙";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
BaseFun.trueDamage(resPet,dstPet,120);
    }
}
