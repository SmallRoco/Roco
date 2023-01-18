package content.skill.shui;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillGanLinPuJiang implements SkillInterface {
    String name = "甘霖普降";
    String animal = "magic";
    boolean physical = false;
    int speed = 9;
    String type = "水";
    int power = 0;
    int pp = 5;
    String description = "转变天气为阴雨，为己方全体回复少量生命值。先手+1";
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
        MainFrame.setEnvironment("阴雨",5);
        BaseFun.otherPersRecover(BaseFun.getPetList(resPet),null,false,50);
    }
}
