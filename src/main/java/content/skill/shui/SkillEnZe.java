package content.skill.shui;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillEnZe implements SkillInterface {
    String name = "恩泽";
    String animal = "magic";
    boolean physical = false;
    int speed = 9;
    String type = "水";
    int power = 0;
    int pp = 20;
    String description = "消耗自身精力治愈场下受伤最重的队友，同时提升自身防御。先手+1";
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

    @Override public String getTuoshou(){return "蓝色水球";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        int change = (int) (resPet.getHp()*0.5);
        resPet.setHp(resPet.getHp()-change);
        Pet petOfMinHp = BaseFun.getPetOfMinHp(BaseFun.getPetList(resPet));
        petOfMinHp.setHp(petOfMinHp.getHp()+change*2);
        resPet.statusChange(2,1);
    }
}
