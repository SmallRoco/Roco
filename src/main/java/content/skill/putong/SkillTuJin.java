package content.skill.putong;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillTuJin implements SkillInterface {
    String name = "突进";
    String animal = "att1";
    boolean physical = true;
    int speed = 8;
    String type = "普通";
    int power = 90;
    int pp = 20;
    String description = "靠近对方攻击，但自己也会受伤。";
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
        resPet.setHp(resPet.getHp()-20);
    }
}
