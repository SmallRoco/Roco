package content.skill.cao;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;
import content.space.Skill.shui.SkillYangGuangLieYanS;
import content.space.SpaceBase;

public class SkillYangGuangLieYan implements SkillInterface {
    String name = "阳光烈焰";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "草";
    int power = 120;
    int pp = 10;
    String description = "本回合准备，下回合发动攻击，给对手造成巨大伤害。";
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
    public String getTuoshou() {
        return "光明增益";
    }

    @Override
    public void skill(Pet resPet, Pet dstPet) {
        resPet.addSpaces(name,new SpaceBase(2,resPet== MainFrame.pet1,new SkillYangGuangLieYanS()));
    }
}
