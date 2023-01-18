package content.skill.huo;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillHoShaoMeiMao implements SkillInterface {

    String name = "火烧眉毛";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "火";
    int power = 0;
    int pp = 15;
    String description = "5回合免疫睡眠";
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
        resPet.addMark("睡眠免疫",5);
    }
}
