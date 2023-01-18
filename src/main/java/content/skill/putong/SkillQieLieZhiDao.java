package content.skill.putong;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillQieLieZhiDao implements SkillInterface {
    String name = "切裂之刀";
    String animal = "att1";
    boolean physical = true;
    int speed = 8;
    String type = "普通";
    int power = 70;
    int pp = 20;
    String description = "一定机率提高暴击率。";
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
    public void skill(Pet resPet, Pet dstPet) {
        BaseFun.att(resPet,dstPet,power,physical,type);
        if(BaseFun.is(45)){
            resPet.setBoom(resPet.getBoom()+100);
        }
    }
}
