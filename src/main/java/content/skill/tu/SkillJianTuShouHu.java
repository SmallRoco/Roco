package content.skill.tu;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillJianTuShouHu implements SkillInterface {
    String name = "坚土守护";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "土";
    int power = 0;
    int pp = 20;
    String description = "提升自己防御等级。";
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

@Override public String getTuoshou(){return "加强防御";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        resPet.statusChange(2,2);
    }
}
