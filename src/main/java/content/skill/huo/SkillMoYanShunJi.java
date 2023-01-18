package content.skill.huo;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillMoYanShunJi implements SkillInterface {
    String name = "魔焰瞬击";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "火";
    int power = 90;
    int pp = 10;
    String description = "提升自己的魔攻等级。";
    @Override
    public String getAnimal() {
        return animal;
    }
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        resPet.statusChange(1,1);

        BaseFun.att(resPet,dstPet,power,physical,type);

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
    @Override public String getTuoshou(){return "魔焰瞬击";};
}
