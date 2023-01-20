package content.skill.new1;

import Config.ConfigFile;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillChunBai implements SkillInterface {
    String name = "纯白";
    String animal = "magic";
    boolean physical = false;
    int speed = 10;
    String type = "冰";
    int power = 0;
    int pp = 6;
    String description = "以纯白雾气保护自身，重置并暂时锁定敌我双方的属性能力等级。先手+2";
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

    @Override public String getTuoshou(){return "光明增益";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {

        int zanShi = ConfigFile.get("暂时");

        resPet.clearBuff();
        resPet.setLockBuffCount(zanShi);
        dstPet.clearBuff();
        dstPet.setLockBuffCount(zanShi);


    }
}
