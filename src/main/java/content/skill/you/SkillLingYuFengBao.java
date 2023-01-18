package content.skill.you;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillLingYuFengBao implements SkillInterface {
    String name = "灵域风暴";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "幽";
    int power = 100;
    int pp = 10;
    String description = "如果本轮先手，则提升魔攻2级。如果本轮后手，则提升速度1级。";
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

@Override public String getTuoshou(){return "风之打击";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        if(MainFrame.first){resPet.statusChange(1,2);}else {
            resPet.statusChange(4,1);
        }
        BaseFun.att(resPet,dstPet,power,physical,type);
    }
}
