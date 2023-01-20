package content.skill.new1;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillHanBingCaiJue implements SkillInterface {
    String name = "寒冰裁决";
    String animal = "att1";
    boolean physical = false;
    int speed = 9;
    String type = "冰";
    int power = 100;
    int pp = 20;
    String description = "高命中技能，会暂时以寒气削减对方的攻击力。先手+1";
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
    public double hitUp() {
        return 2.5;
    }

    @Override
    public void skill(Pet resPet, Pet dstPet) {
        BaseFun.att(resPet,dstPet,power,physical,type);
        if(BaseFun.is(40)){
            dstPet.statusChange(0,-1);
        }
        dstPet.statusChange(0,-1);
    }
}
