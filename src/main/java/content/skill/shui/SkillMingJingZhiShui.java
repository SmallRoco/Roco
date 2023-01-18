package content.skill.shui;

import Config.ConfigFile;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillMingJingZhiShui implements SkillInterface {
    String name = "明镜止水";
    String animal = "magic";
    boolean physical = false;
    int speed = 12;
    String type = "水";
    int power = 0;
    int pp = 4;
    String description = "立即净化自身，回复25%最大生命值，并暂时免疫异常状态。先手+4";
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
    @Override public boolean doSelf(){return true;}
    @Override public String getTuoshou(){return "蓝色水球";};


    @Override
    public void skill(Pet resPet, Pet dstPet) {
        //净化自身
        resPet.clearSelf();
        //回血
        resPet.setHp((int)(resPet.getHp()+resPet.getBaseHp()*0.25));
        //两回合免疫异常
        resPet.setNoexception(ConfigFile.get("暂时"));
    }
}
