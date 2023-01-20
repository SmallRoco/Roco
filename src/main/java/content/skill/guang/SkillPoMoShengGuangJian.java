package content.skill.guang;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillPoMoShengGuangJian implements SkillInterface {
    String name = "破魔圣光箭";
    String animal = "att1";
    boolean physical = true;
    int speed = 9;
    String type = "光";
    int power = 120;
    int pp = 20;
    String description = "射出贯穿苍穹的破魔圣箭，必定命中，且会斩杀血量低于200的敌人。先手+1";
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

        if(dstPet.getHp()<200){
            BaseFun.trueDamage(resPet,dstPet,999);
        }else {
            BaseFun.att(resPet, dstPet, power, physical, type);
        }
    }

    @Override
    public boolean hitMust() {
        return true;
    }
}
