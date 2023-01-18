package content.skill.shui;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillQiPaoShu implements SkillInterface {
    String name = "气泡术";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "水";
    int power = 40;
    int pp = 30;
    String description = "给对手造成伤害，有一定几率令对手速度等级下降。";
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

@Override public String getTuoshou(){return "泡沫攻击";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        if(BaseFun.is(60)){
            dstPet.statusChange(4,-2);
        }
        BaseFun.att(resPet,dstPet,power,physical,type);
    }
}
