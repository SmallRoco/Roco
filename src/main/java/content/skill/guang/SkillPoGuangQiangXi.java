package content.skill.guang;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillPoGuangQiangXi implements SkillInterface {
    String name = "破光强袭";
    String animal = "att1";
    boolean physical = true;
    int speed = 8;
    String type = "光";
    int power = 0;
    int pp = 5;
    String description = "对手的防御等级越高，攻击威力就越大。";
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
        BaseFun.att(resPet,dstPet,40*dstPet.getAppendStatus()[2],physical,type);
    }
}
