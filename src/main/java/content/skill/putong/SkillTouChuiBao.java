package content.skill.putong;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;
import content.space.Skill.shui.SkillTouChuiBaoS;
import content.space.SpaceBase;

public class SkillTouChuiBao implements SkillInterface {
    String name = "头槌爆";
    String animal = "att1";
    boolean physical = true;
    int speed = 8;
    String type = "普通";
    int power = 30;
    int pp = 15;
    String description = "提高防御等级，下回合连续攻击。";
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
    public void skill(Pet resPet, Pet dstPet) {
        resPet.statusChange(2,1);
        BaseFun.att(resPet,dstPet,power,physical,type);
        resPet.addSpaces(name,new SpaceBase(2,resPet== MainFrame.pet1,new SkillTouChuiBaoS()));
    }
}
