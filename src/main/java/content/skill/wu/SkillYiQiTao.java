package content.skill.wu;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillYiQiTao implements SkillInterface {
    String name = "一骑讨";
    String animal = "att1";
    boolean physical = true;
    int speed = 10;
    String type = "武";
    int power = 90;
    int pp = 8;
    String description = "造成物理伤害同时使敌方虚弱，并强制对手之后4回合内无法更换宠物。先手+2";
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
        dstPet.statusChange(2,-1);
        dstPet.statusChange(3,-1);
        dstPet.addMark("无法换宠",5);
        BaseFun.att(resPet,dstPet,power,physical,type);
        if(resPet.getMark("苍龙庇佑")!=null){
            resPet.setHp(resPet.getHp()+75);
        }
    }
}
