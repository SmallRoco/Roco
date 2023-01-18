package content.skill.cao;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillBaoLiJiQu implements SkillInterface {
    String name = "暴力汲取";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "草";
    int power = 100;
    int pp = 10;
    String description = "给对手造成伤害，并且自己回复一定的HP。";
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

    @Override public String getTuoshou(){return "超级吸取";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        int att = BaseFun.att(resPet, dstPet, power, physical, type);
        resPet.setHp(resPet.getHp()+att*0.4);
    }
}
