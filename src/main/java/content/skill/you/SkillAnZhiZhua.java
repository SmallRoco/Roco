package content.skill.you;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillAnZhiZhua implements SkillInterface {
    String name = "暗之爪";
    String animal = "att1";
    boolean physical = true;
    int speed = 8;
    String type = "幽";
    int power = 70;
    int pp = 15;
    String description = "提高暴击机率。";
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
        dstPet.setBoom((int)(dstPet.getBaseBoom()*1.2));
    }
}
