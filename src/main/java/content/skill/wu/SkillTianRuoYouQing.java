package content.skill.wu;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillTianRuoYouQing implements SkillInterface {
    String name = "天若有情";
    String animal = "att1";
    boolean physical = true;
    int speed = 9;
    String type = "武";
    int power = 125;
    int pp = 10;
    String description = "造成两段伤害，或是回复自身部分生命值。先手+1";
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
        if(resPet.getMark("苍龙庇佑")!=null){
            resPet.setHp(resPet.getHp()+75);
        }
        if(BaseFun.is(50)){
            BaseFun.att(resPet,dstPet, (int) (power*0.5),physical,type);
        }else {
            resPet.setHp(resPet.getHp()+resPet.getBaseHp()*0.20);
        }
    }
}
