package content.skill.tu;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillNiJiangFeiJian implements SkillInterface {
    String name = "泥浆飞溅";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "土";
    int power = 55;
    int pp = 15;
    String description = "令对手速度等级下降。";
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

@Override public String getTuoshou(){return "泥浆弹";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        dstPet.statusChange(4,-1);
        BaseFun.att(resPet,dstPet,power,physical,type);
    }
}
