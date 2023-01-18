package content.skill.cao;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;
import content.space.BattleSpace;
import content.space.SpaceCaoZhiShouHu;
import content.space.SpaceInterface;

public class SkillCaoZhiShouHu implements SkillInterface {
    String name = "草之守护";
    String animal = "magic";
    boolean physical = false;
    int speed = 12;
    String type = "草";
    int power = 0;
    int pp = 3;
    String description = "被草系守护，连续三回合发动技能，首回合闪避敌方技能，后续两回合每回合随机为自身提供正面强化效果。先手+4";
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

    @Override public String getTuoshou(){return "光明增益";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {

        resPet.setNoDamage(1);
        resPet.addSpaces(name,new SpaceCaoZhiShouHu(2,resPet==MainFrame.pet1));
    }
}
