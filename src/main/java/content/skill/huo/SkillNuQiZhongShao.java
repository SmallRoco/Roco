package content.skill.huo;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;
import content.space.Skill.huo.SkillNuQiZhongShaoS;
import content.space.SpaceBase;
import content.space.SpaceOneInterface;

public class SkillNuQiZhongShao implements SkillInterface {
    String name = "怒气中烧";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "火";
    int power = 0;
    int pp = 5;
    String description = "5回合内，暴击概率提升。";
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

    @Override public boolean doSelf(){return true;};

@Override public String getTuoshou(){return "光明增益";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {

        resPet.setBoom(resPet.getBaseBoom()+150);
        SpaceBase spaceBase = new SpaceBase(5, resPet == MainFrame.pet1,new SkillNuQiZhongShaoS());
        resPet.addSpaces(name,spaceBase);
    }


}
