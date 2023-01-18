package content.skill.shui;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillShuiZhiBoWen implements SkillInterface {
    String name = "水之波纹";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "水";
    int power = 60;
    int pp = 29;
    String description = "一定机率让对手混乱。";
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

    @Override public String getTuoshou(){return "水之波纹";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        if(BaseFun.is(50)){
            dstPet.addStatus("混乱");
        }
        BaseFun.att(resPet,dstPet,power,physical,type);
    }
}
