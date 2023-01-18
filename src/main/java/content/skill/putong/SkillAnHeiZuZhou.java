package content.skill.putong;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillAnHeiZuZhou implements SkillInterface {
    String name = "暗黑诅咒";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "普通";
    int power = 0;
    int pp = 10;
    String description = "令对手速度等级下降。";
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

@Override public String getTuoshou(){return "恶魔之眼";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {

        dstPet.statusChange(4,-3);

    }
}
