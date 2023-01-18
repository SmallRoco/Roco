package content.skill.shui;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;
import content.space.Skill.shui.SkillYanMeiS;
import content.space.Skill.shui.SkillZhiXiS;
import content.space.SpaceBase;

public class SkillYanMei implements SkillInterface {
    String name = "淹没";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "水";
    int power = 0;
    int pp = 5;
    String description = "5回合内，让对手持续受到一定的伤害。";
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

@Override public String getTuoshou(){return "冰天雪地";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {


        BaseFun.trueDamage(resPet, dstPet, 80);

        resPet.addSpaces(name,new SpaceBase(5,resPet== MainFrame.pet1,new SkillYanMeiS()));

    }

    @Override
    public void miss(Pet resPet, Pet dstPet) {
        resPet.addSpaces(name,new SpaceBase(5,resPet== MainFrame.pet1,new SkillZhiXiS()));
    }
}
