package content.skill.shui;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillYeTaiShuiHuan implements SkillInterface {

    String name = "液态水环";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "水";
    int power = 0;
    int pp = 20;
    String description = "回复自身一定的HP。";
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
        //回血45%
        resPet.setHp((int)(resPet.getHp()+resPet.getBaseHp()*0.45));

    }
}
