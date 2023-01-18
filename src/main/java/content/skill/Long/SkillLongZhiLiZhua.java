package content.skill.Long;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillLongZhiLiZhua implements SkillInterface {
    String name = "龙之利爪";
    String animal = "magic";
    boolean physical = true;
    int speed = 8;
    String type = "龙";
    int power = 80;
    int pp = 15;
    String description = "较强的攻击力，一定机率令对手进入烧伤状态。";
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

    @Override public String getTuoshou() { return "龙之爪牙"; }

    @Override
    public void skill(Pet resPet, Pet dstPet) {

        BaseFun.att(resPet,dstPet,power,physical,type);
        if(BaseFun.is(50)){
            dstPet.addStatus("烧伤");
        }
    }
}
