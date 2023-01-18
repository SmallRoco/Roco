package content.skill.huo;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillBaoJiQiang implements SkillInterface {
    String name = "爆击枪";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "火";
    int power = 40;
    int pp = 25;
    String description = "一定机率让对手进入烧伤状态。";
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

@Override public String getTuoshou(){return "暴击枪";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        BaseFun.att(resPet,dstPet,power,physical,type);
        if(BaseFun.is(45)){
            dstPet.addStatus("烧伤");
        }
    }
}
