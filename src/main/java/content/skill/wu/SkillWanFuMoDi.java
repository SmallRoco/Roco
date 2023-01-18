package content.skill.wu;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillWanFuMoDi implements SkillInterface {
    String name = "万夫莫敌";
    String animal = "att1";
    boolean physical = true;
    int speed = 10;
    String type = "武";
    int power = 100;
    int pp = 12;
    String description = "提升一级物攻与命中等级，再对敌方造成巨大伤害。先手+2";
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
        resPet.statusChange(0,1);
        resPet.statusChange(6,1);
        BaseFun.att(resPet,dstPet,power,physical,type);
        if(resPet.getMark("苍龙庇佑")!=null){
            resPet.setHp(resPet.getHp()+75);
        }
    }

    @Override
    public void miss(Pet resPet, Pet dstPet) {
        resPet.statusChange(0,1);
        resPet.statusChange(6,1);
    }
}
