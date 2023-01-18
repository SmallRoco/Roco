package content.skill.huo;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillLieYanChongFeng implements SkillInterface {
    String name = "烈焰冲锋";
    String animal = "att1";
    boolean physical = true;
    int speed = 8;
    String type = "火";
    int power = 120;
    int pp = 15;
    String description = "一定机率让对手进入烧伤状态，对手受到的伤害一部分反射给自己。";
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
        resPet.setHp(resPet.getHp()-BaseFun.att(resPet,dstPet,power,physical,type)*0.2);
        if(BaseFun.is(50)){
            dstPet.addStatus("烧伤");
        }
    }
}
