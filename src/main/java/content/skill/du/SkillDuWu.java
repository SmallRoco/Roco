package content.skill.du;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillDuWu implements SkillInterface {
    String name = "毒雾";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "毒";
    int power = 0;
    int pp = 35;
    String description = "让对手中毒。";
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

    @Override public String getTuoshou(){return "催眠粉";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        dstPet.addStatus("中毒");
    }
}
