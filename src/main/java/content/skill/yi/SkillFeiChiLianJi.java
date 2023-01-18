package content.skill.yi;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillFeiChiLianJi implements SkillInterface {
    String name = "飞翅连击";
    String animal = "att1";
    boolean physical = true;
    int speed = 8;
    String type = "翼";
    int power = 60;
    int pp = 35;
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

    @Override
    public void skill(Pet resPet, Pet dstPet) {
BaseFun.att(resPet,dstPet,power,physical,type);
    }
}
