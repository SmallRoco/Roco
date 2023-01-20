package content.skill.new1;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;
import content.space.Skill.bing.SkillBingTianXueDiS;
import content.space.SpaceBase;

public class SkillBingTianXueDi implements SkillInterface {
    String name = "冰天雪地";
    String animal = "magic";
    boolean physical = false;
    int speed = 9;
    String type = "冰";
    int power = 0;
    int pp = 10;
    String description = "6回合内，双方都受到伤害。对冰系宠物无效。对方换宠依然有效。先手+1";
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
    public boolean doSelf() {
        return true;
    }

    @Override public String getTuoshou(){return "冰天雪地";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {

        if(!resPet.getType().contains("冰")){
            resPet.setHp(resPet.getHp()-resPet.getBaseHp()/5.0);
        }
        if(!dstPet.getType().contains("冰")){
            dstPet.setHp(dstPet.getHp()-dstPet.getBaseHp()/5.0);
        }

        resPet.addSpaces(name,new SpaceBase(6,resPet== MainFrame.pet1,new SkillBingTianXueDiS()));
    }
}
