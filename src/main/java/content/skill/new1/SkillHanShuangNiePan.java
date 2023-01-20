package content.skill.new1;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillHanShuangNiePan implements SkillInterface {
    String name = "寒霜涅槃";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "冰";
    int power = 0;
    int pp = 5;
    String description = "（没写,别用）";
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

@Override public String getTuoshou(){return "翅膀增益";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {


    }
}
