package content.skill.guang;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;
import content.space.Skill.shui.SkillShuiZhiHuDunS;
import content.space.SpaceBase;

public class SkillLiuGuangHuDun implements SkillInterface {
    String name = "流光护盾";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "光";
    int power = 0;
    int pp = 10;
    String description = "形成一个持续5回合的护盾，每回合可以多吸收60点伤害。";
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

    @Override public String getTuoshou(){return "加强防御";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        resPet.addSpaces(name,new SpaceBase(5,resPet== MainFrame.pet1,new SkillShuiZhiHuDunS()));
    }
}
