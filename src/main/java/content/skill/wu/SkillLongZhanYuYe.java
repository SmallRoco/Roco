package content.skill.wu;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillLongZhanYuYe implements SkillInterface {
    String name = "龙战于野";
    String animal = "magic";
    boolean physical = false;
    int speed = 12;
    String type = "武";
    int power = 0;
    int pp = 6;
    String description = "净化自身解除所有异常状态与负面强化，提升自身1级闪避等级和1级命中等级，暂时免疫异常状态与负面强化，受苍龙庇佑，4回合内每次攻击回复75生命值。先手+4";
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

    @Override public String getTuoshou(){return "翅膀增益";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        resPet.clearSelf();
        resPet.statusChange(5,1);
        resPet.statusChange(6,1);
        resPet.setNoDeBuff(2);
        resPet.setNoexception(2);
        resPet.setMark("苍龙庇佑",4);
    }
}
