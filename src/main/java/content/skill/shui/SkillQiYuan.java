package content.skill.shui;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillQiYuan implements SkillInterface {
    String name = "祈愿";
    String animal = "magic";
    boolean physical = false;
    int speed = 9;
    String type = "水";
    int power = 0;
    int pp = 20;
    String description = "立即恢复自身部分精力，场下受伤队友也会恢复少许。先手+1";
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

    @Override public String getTuoshou(){return "翅膀增益";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {

        resPet.setHp(resPet.getHp()+resPet.getBaseHp()*0.3);
        BaseFun.otherPersRecover(BaseFun.getPetList(resPet),resPet,true,10);

    }
}
