package content.skill.wu;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillCangLongZhuiYue implements SkillInterface {
    String name = "苍龙追月";
    String animal = "att1";
    boolean physical = true;
    int speed = 9;
    String type = "武";
    int power = 120;
    int pp = 12;
    String description = "苍云追月式，造成物理伤害同时，提升自身1级物攻和1级速度等级。先手+1";
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

        BaseFun.att(resPet,dstPet,power,physical,type);
        if(resPet.getMark("苍龙庇佑")!=null){
            resPet.setHp(resPet.getHp()+75);
        }
        resPet.statusChange(0,1);
        resPet.statusChange(4,1);
    }
}
