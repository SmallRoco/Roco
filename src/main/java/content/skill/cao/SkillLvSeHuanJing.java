package content.skill.cao;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;
import content.space.Skill.shui.SkillLvSeHuanJingS;
import content.space.SpaceBase;

public class SkillLvSeHuanJing implements SkillInterface {
    String name = "绿色幻境";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "草";
    int power = 0;
    int pp = 5;
    String description = "使用后5回合内，每回合恢复一定的HP。";
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

    @Override public String getTuoshou(){return "光明增益";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {

        resPet.setHp(resPet.getHp()+60);
        resPet.addSpaces("绿色幻境",new SpaceBase(5,resPet== MainFrame.pet1,new SkillLvSeHuanJingS()));

    }
}
