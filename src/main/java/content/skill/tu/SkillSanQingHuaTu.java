package content.skill.tu;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillSanQingHuaTu implements SkillInterface {
    String name = "三清化土";
    String animal = "att1";
    boolean physical = false;
    int speed = 8;
    String type = "土";
    int power = 0;
    int pp = 15;
    String description = "造成巨大伤害。";
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
        BaseFun.trueDamage(resPet,dstPet,200);
    }
}
