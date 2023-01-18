package content.skill.shui;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillQingShuiHunShang implements SkillInterface {
    String name = "清水魂伤";
    String animal = "att1";
    boolean physical = false;
    int speed = 8;
    String type = "水";
    int power = 100;
    int pp = 10;
    String description = "在攻击对方的同时，自己也受到一定的伤害。";
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

@Override public String getTuoshou(){return null;};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        int att = BaseFun.att(resPet, dstPet, power, physical, type);
        resPet.setHp(resPet.getHp()-att*0.20);
    }
}
