package content.skill.guang;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillHuanGuangQianYing implements SkillInterface {
    String name = "幻光千影";
    String animal = "att1";
    boolean physical = true;
    int speed = 12;
    String type = "光";
    int power = 80;
    int pp = 10;
    String description = "召唤数千道幻光消除对方的强化并攻击对方，若光系队友数量为3只及以上，则本技能必定暴击。先手+4";
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
        if(BaseFun.getPetsCountByType(BaseFun.getPetList(resPet),"光")>3){
            BaseFun.boomAtt(resPet,dstPet,power,physical,type);
            return;
        }
        BaseFun.att(resPet,dstPet,power,physical,type);
    }
}
