package content.skill.emo;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillXinYueLuanWu implements SkillInterface {
    String name = "新月乱舞";
    String animal = "att1";
    boolean physical = false;
    int speed = 9;
    String type = "恶魔";
    int power = 100;
    int pp = 15;
    String description = "召唤无数个新月银刃攻击对方，使对方恐惧。血月天气下会削弱对方伤害。先手+1";
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
        dstPet.addStatus("恐惧");
        if(MainFrame.getEnvironment().equals("血月")){
            resPet.setMaxDamaged((int) (resPet.getBaseHp()*0.4),1);
        }
    }
}
