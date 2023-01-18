package content.skill.shui;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillGuaiShenZhiLi implements SkillInterface {
    String name = "怪神之力";
    String animal = "att1";
    boolean physical = true;
    int speed = 8;
    String type = "水";
    int power = 80;
    int pp = 5;
    String description = "随机让对手进入冰冻、中毒状态，或是降低速度等级。";
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
        int random = BaseFun.getRandom(10);
        if(random<2){
            dstPet.addStatus("冰冻");
        }else if(random<5){
            dstPet.addStatus("中毒");
        }else {
            dstPet.statusChange(4,-1);
        }

    }
}
