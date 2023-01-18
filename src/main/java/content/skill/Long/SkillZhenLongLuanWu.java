package content.skill.Long;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;
import content.space.BattleSpace;
import content.space.Skill.Long.SkillZhenLongLuanWuS;
import content.space.SpaceBase;

public class SkillZhenLongLuanWu implements SkillInterface {
    String name = "真龙乱舞";
    String animal = "att1";
    boolean physical = true;
    int speed = 8;
    String type = "龙";
    int power = 100;
    int pp = 10;
    String description = "连续三回合对敌方造成伤害。";
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
        BaseFun.att(resPet,dstPet,60,physical,type);
        if(resPet.getMark("苍龙庇佑")!=null){
            resPet.setHp(resPet.getHp()+75);
        }
        resPet.addSpaces(name,new SpaceBase(3,resPet== MainFrame.pet1,new SkillZhenLongLuanWuS()));
    }

    @Override
    public void miss(Pet resPet, Pet dstPet) {
        resPet.addSpaces(name,new SpaceBase(3,resPet== MainFrame.pet1,new SkillZhenLongLuanWuS()));
    }
}
