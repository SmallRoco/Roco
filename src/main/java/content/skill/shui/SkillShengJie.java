package content.skill.shui;

import Config.ConfigFile;
import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;
import content.space.Skill.shui.SkillShengJieS;
import content.space.SpaceBase;

public class SkillShengJie implements SkillInterface {
    String name = "圣洁";
    String animal = "magic";
    boolean physical = false;
    int speed = 12;
    String type = "水";
    int power = 0;
    int pp = 3;
    String description = "主动：驱散负面状态，短期内闪避提升；被动：免疫睡眠、麻醉、诅咒。先手+4";
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
        resPet.clearException();
        resPet.addSpaces(name,new SpaceBase(ConfigFile.get("短期"),resPet== MainFrame.pet1,new SkillShengJieS()));
    }

    @Override
    public void init(Pet pet){
        pet.addMark("睡眠免疫",1000);
        pet.addMark("麻痹免疫",1000);
        pet.addMark("诅咒免疫",1000);
    }
}
