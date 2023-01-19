package content.skill.new1;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;
import content.space.Skill.shui.SkillBingDongS;
import content.space.SpaceBase;

public class SkillBingDong implements SkillInterface {
    String name = "冰冻";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "水";
    int power = 15;
    int pp = 15;
    String description = "2-5回合内，敌人无法替换宠物，并持续伤血。";
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

@Override public String getTuoshou(){return "冰晶结界";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        BaseFun.att(resPet,dstPet,power,physical,type);
        int count = 2+BaseFun.getRandom(4);
        dstPet.setMark("无法换宠",count);
        resPet.addSpaces(name,new SpaceBase(count,resPet== MainFrame.pet1,new SkillBingDongS()));
    }
}
