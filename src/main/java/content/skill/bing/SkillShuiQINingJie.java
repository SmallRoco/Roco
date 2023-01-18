package content.skill.bing;

import content.Pet;
import content.BaseFun;
import content.SkillInterface;

public class SkillShuiQINingJie implements SkillInterface {
    String name = "水汽凝结";
    String animal = "magic";
    boolean physical = false;
    int speed = 9;
    String type = "冰";
    int power = 100;
    int pp = 15;
    String description = "有概率冻结敌人，对水和神水系敌人效果拔群。先手+1";
    @Override
    public String getAnimal() {
        return animal;
    }
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        BaseFun.att(resPet,dstPet,power,physical,type);
        if(BaseFun.is(80)){
            dstPet.addStatus("冰冻");
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
    @Override public String getTuoshou() { return "冰晶结界"; }
}
