package content.skill.emo;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillHeiDong implements SkillInterface {
    String name = "黑洞";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "恶魔";
    int power = 0;
    int pp = 10;
    String description = "令对手进入睡眠状态。";
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

    @Override public String getTuoshou(){return "迷之微笑";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        dstPet.addStatus("睡眠");
    }
}
