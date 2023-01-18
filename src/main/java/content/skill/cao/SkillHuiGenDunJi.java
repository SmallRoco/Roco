package content.skill.cao;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillHuiGenDunJi implements SkillInterface {
    String name = "慧根盾击";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "草";
    int power = 90;
    int pp = 10;
    String description = "提升自身的魔攻等级。";
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

@Override public String getTuoshou(){return "灵蔓盾击";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        resPet.statusChange(1,1);
BaseFun.att(resPet,dstPet,power,physical,type);
    }
}
