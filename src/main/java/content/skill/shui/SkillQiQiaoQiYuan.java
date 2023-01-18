package content.skill.shui;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillQiQiaoQiYuan implements SkillInterface {
    String name = "七巧祈愿";
    String animal = "att1";
    boolean physical = false;
    int speed = 8;
    String type = "水";
    int power = 120;
    int pp = 10;
    String description = "七月七夕，祈福许愿。清除自身负面。若对方处于冰冻状态则造成暴击伤害。若对方无冰冻状态则随机触发一种效果：降低敌方治疗效果两回合、回复精力和强化自身一级魔攻与魔抗。";
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
    @Override public String getTuoshou(){return "蓝色水球";};

    @Override
    public void skill(Pet resPet, Pet dstPet) {
        resPet.clearNoDebuff();
        if(dstPet.getStatus("冰冻")!=null){
            BaseFun.att(resPet,dstPet,power,physical,type);
        }else {


            if (BaseFun.is(33)) {
                resPet.setRecover(2,60);
            } else if (BaseFun.is(33)) {
                resPet.setHp((int) (resPet.getHp() + resPet.getBaseHp() * 0.25));
            } else {
                resPet.statusChange(1, 1);
                resPet.statusChange(3, 1);
            }
        }
        BaseFun.att(resPet, dstPet, power, physical, type);


    }
}
