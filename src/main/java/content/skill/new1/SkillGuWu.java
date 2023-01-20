package content.skill.new1;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillGuWu implements SkillInterface {
    String name = "鼓舞";
    String animal = "magic";
    boolean physical = false;
    int speed = 9;
    String type = "水";
    int power = 0;
    int pp = 5;
    String description = "为己方全员提供精力值回复，还会随机强化自身。先手+1";
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

@Override public String getTuoshou(){return "翅膀增幅";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {

        BaseFun.otherPersRecover(BaseFun.getPetList(resPet),null,true,20);
        int random = BaseFun.getRandom(7);
        resPet.statusChange(random,1);


    }
}
