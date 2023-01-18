package content.skill.tu;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillNiJiangDan implements SkillInterface {
    String name = "泥浆弹";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "土";
    int power = 65;
    int pp = 10;
    String description = "一定机率让对手命中等级下降。";
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
        if(BaseFun.is(50)){dstPet.statusChange(6,-1);}
        else if(BaseFun.is(70)){dstPet.statusChange(6,-2);}

        BaseFun.att(resPet,dstPet,power,physical,type);
    }
}
