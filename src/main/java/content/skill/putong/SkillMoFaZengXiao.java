package content.skill.putong;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillMoFaZengXiao implements SkillInterface {
    String name = "魔法增效";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "普通";
    int power = 0;
    int pp = 40;
    String description = "提高自己的魔攻等级。";
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

    @Override public String getTuoshou(){return "翅膀增益";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        resPet.statusChange(1,2);
    }
}
