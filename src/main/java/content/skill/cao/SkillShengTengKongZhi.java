package content.skill.cao;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillShengTengKongZhi implements SkillInterface {
    String name = "圣藤控制";
    String animal = "magic";
    boolean physical = false;
    int speed = 11;
    String type = "草";
    int power = 0;
    int pp = 5;
    String description = "让对手进入睡眠状态，同时降低对方魔抗等级2级。先手+3";
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
    @Override public String getTuoshou(){return "迷之微笑";};

    @Override
    public void skill(Pet resPet, Pet dstPet) {
        dstPet.statusChange(3,-2);
        dstPet.addStatus("睡眠");


    }
}
