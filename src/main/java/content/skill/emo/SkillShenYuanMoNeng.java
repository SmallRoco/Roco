package content.skill.emo;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillShenYuanMoNeng implements SkillInterface {
    String name = "深渊魔能";
    String animal = "magic";
    boolean physical = false;
    int speed = 12;
    String type = "恶魔";
    int power = 0;
    int pp = 5;
    String description = "提升2级魔攻，并为觉醒之外的其他技能恢复PP值。先手+4";
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

@Override public String getTuoshou(){return "黑暗增益";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        resPet.statusChange(1,2);
        BaseFun.changeOtherSkillPp(resPet,name,1);
    }
}
