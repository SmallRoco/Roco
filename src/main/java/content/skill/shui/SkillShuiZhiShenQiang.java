package content.skill.shui;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillShuiZhiShenQiang implements SkillInterface {
    String name = "水之神枪";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "水";
    int power = 40;
    int pp = 25;
    String description = "给对手较重的伤害。";
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

@Override public String getTuoshou(){return "白色光束";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
BaseFun.att(resPet,dstPet,power*2,physical,type);
    }
}
