package content.skill.cao;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillCuiMianFen implements SkillInterface {
    String name = "催眠粉";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "草";
    int power = 0;
    int pp = 15;
    String description = "一定机率让对手昏睡。";

    @Override
    public String getAnimal() {
        return animal;
    }
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        if(BaseFun.is(80)){
            dstPet.addStatus("睡眠");
        }
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
    @Override public String getTuoshou(){return "催眠粉";}
}
