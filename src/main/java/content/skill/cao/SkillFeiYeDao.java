package content.skill.cao;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillFeiYeDao implements SkillInterface {
    String name = "飞叶刀";
    String animal = "magic";
    boolean physical = true;
    int speed = 8;
    String type = "草";
    int power = 55;
    int pp = 20;
    String description = "一定机率提高暴击等级。";
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
    @Override public String getTuoshou(){return "飞叶刀";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        if(BaseFun.is(80)){resPet.setBoom(resPet.getBoom()+200);}
        BaseFun.att(resPet,dstPet,power,physical,type);
    }
}
