package content.skill.Long;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillWeiLanLongXi implements SkillInterface {
    String name = "蔚蓝龙息";
    String animal = "magic";
    boolean physical = false;
    int speed = 9;
    String type = "龙";
    int power = 100;
    int pp = 5;
    String description = "含有闪电与霹雳的龙息，有几率麻醉敌人。先手+1";
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

@Override public String getTuoshou(){return "魔焰瞬击";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        BaseFun.att(resPet,dstPet,power,physical,type);
        if(resPet.getMark("苍龙庇佑")!=null){
            resPet.setHp(resPet.getHp()+75);
        }
        if(BaseFun.is(60)){
            dstPet.addStatus("麻痹");
        }
    }
}
