package content.skill.putong;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillHuDunShu implements SkillInterface {
    String name = "护盾术";
    String animal = "magic";
    boolean physical = false;
    int speed = 11;
    String type = "普通";
    int power = 0;
    int pp = 10;
    String description = "保护自己本回合不受伤害，但是连续使用可能失败。先手+3";
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
    @Override public boolean doSelf() { return true; }

@Override public String getTuoshou(){return "加强防御";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        if(resPet.getSkills().get(MainFrame.useSkillOld[BaseFun.getIndexPawn(resPet)]).getName().equals(name)){
            if(BaseFun.is(40)){
                return;
            }
        }
        resPet.setNoDamage(1);
    }
}
