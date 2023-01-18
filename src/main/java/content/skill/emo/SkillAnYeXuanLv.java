package content.skill.emo;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillAnYeXuanLv implements SkillInterface {
    String name = "暗夜旋律";
    String animal = "magic";
    boolean physical = false;
    int speed = 12;
    String type = "恶魔";
    int power = 0;
    int pp = 4;
    String description = "演奏一首暗夜奏鸣曲净化自身。4回合内免疫异常，回复生命，效果随PP值变化，召唤血月天气5回合，若演奏被打断会重新开始。先手+4";
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

    @Override public String getTuoshou(){return "恶魔之眼";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        resPet.clearSelf();
        resPet.setNoexception(4);
        resPet.setHp(resPet.getHp()+resPet.getBaseHp()*0.15*(4-resPet.getPps()[BaseFun.getSkillIndex(resPet,name)]));
        MainFrame.setEnvironment("血月",5);



    }
}
