package content.skill.cao;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillHuaBanQunWu implements SkillInterface {
    String name = "花瓣群舞";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "草";
    int power = 90;
    int pp = 20;
    String description = "给对手造成一定伤害，有概率使对方混乱。";
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

    @Override public String getTuoshou(){return "花瓣群舞";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        if(BaseFun.is(40)){
            dstPet.addStatus("混乱");
        }
        BaseFun.att(resPet,dstPet,power,physical,type);
    }
}
