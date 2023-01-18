package content.skill.jixie;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillGuangZhaPao implements SkillInterface {
    String name = "光栅炮";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "机械";
    int power = 80;
    int pp = 10;
    String description = "一定机率降低对手的魔抗等级。";
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

    @Override public String getTuoshou(){return "白色光束";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        if(BaseFun.is(80)){
            dstPet.statusChange(3,-2);
        }
        BaseFun.att(resPet,dstPet,power,physical,type);
    }
}
