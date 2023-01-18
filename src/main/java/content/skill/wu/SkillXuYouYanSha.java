package content.skill.wu;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillXuYouYanSha implements SkillInterface {
    String name = "虚诱掩杀";
    String animal = "att1";
    boolean physical = true;
    int speed = 12;
    String type = "武";
    int power = 80;
    int pp = 6;
    String description = "提升自身闪避并造成高额伤害。先手+4";
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
        resPet.statusChange(5,2);
        BaseFun.att(resPet,dstPet,power,physical,type);
        if(resPet.getMark("苍龙庇佑")!=null){
            resPet.setHp(resPet.getHp()+75);
        }
    }

    @Override
    public void miss(Pet resPet, Pet dstPet) {
        resPet.statusChange(5,2);
    }
}
