package content.skill.emo;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;
import content.space.Skill.emo.SkillHuanYueHeiZeS;
import content.space.SpaceBase;

public class SkillHuanYueHeiZe implements SkillInterface {
    String name = "幻月黑泽";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "恶魔";
    int power = 0;
    int pp = 10;
    String description = "将对方困入幻月黑泽中，四回合内连续吸取对方的血量直至对方倒下。自身存在负面强化时，会被激怒。";
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

        resPet.addSpaces(name,new SpaceBase(4,resPet== MainFrame.pet1,new SkillHuanYueHeiZeS()));

    }
}
