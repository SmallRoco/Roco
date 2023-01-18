package content.skill.cao;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;
import content.space.Skill.shui.SkillShuiZhiHuDunS;
import content.space.SpaceBase;

public class SkillYangGuangHuDun implements SkillInterface {
    String name = "阳光护盾";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "草";
    int power = 0;
    int pp = 5;
    String description = "形成一个持续5回合的护盾，每回合最多可以吸收60点伤害。";
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

    @Override public String getTuoshou(){return "水晶盾";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        resPet.addSpaces(name,new SpaceBase(5,resPet== MainFrame.pet1,new SkillShuiZhiHuDunS()));
    }
}
