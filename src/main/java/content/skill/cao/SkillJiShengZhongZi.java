package content.skill.cao;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillJiShengZhongZi implements SkillInterface {
    String name = "寄生种子";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "草";
    int power = 0;
    int pp = 10;
    String description = "在对手体内寄生一颗种子，每回合吸取一定HP。";
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

@Override public String getTuoshou(){return "种子炸弹";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        dstPet.addStatus("寄生");
    }
}
