package content.skill.emo;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillShenDuShuiMian implements SkillInterface {
    String name = "深度睡眠";
    String animal = "magic";
    boolean physical = false;
    int speed = 11;
    String type = "恶魔";
    int power = 0;
    int pp = 5;
    String description = "尝试催眠对手，即使失败也会追加额外效果。先手+3";
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

@Override public String getTuoshou(){return "催眠粉";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        if(BaseFun.is(75)){
            dstPet.addStatus("睡眠");
        }else {
            resPet.statusChange(1,1);
        }
    }
}
