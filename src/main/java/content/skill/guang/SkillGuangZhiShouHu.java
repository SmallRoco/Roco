package content.skill.guang;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillGuangZhiShouHu implements SkillInterface {
    String name = "光之守护";
    String animal = "magic";
    boolean physical = false;
    int speed = 15;
    String type = "光";
    int power = 0;
    int pp = 5;
    String description = "5回合内不会出现异常状态。先手+7";
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
    public boolean doSelf() {
        return true;
    }

    @Override public String getTuoshou(){return "光明增益";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        resPet.setNoexception(5);
    }
}
