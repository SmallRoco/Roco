package content.skill.putong;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillDuoLuoZhiLiao implements SkillInterface {
    String name = "堕落治疗";
    String animal = "magic";
    boolean physical = false;
    int speed = 15;
    String type = "普通";
    int power = 0;
    int pp = 4;
    String description = "使敌方本回合的攻击变为治疗，并转移不利状态。先手+7";
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

    @Override public String getTuoshou(){return "恶魔之眼";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {

        resPet.addMark(name,1);

    }

    @Override
    public void miss(Pet resPet, Pet dstPet) {

        resPet.exceptionTranslate(dstPet);
    }
}
